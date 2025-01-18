package com.abcfitness.ignite.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.abcfitness.ignite.dto.BookingQueryResponseDTO;
import com.abcfitness.ignite.dto.ClassBookingRequestDTO;
import com.abcfitness.ignite.entity.Booking;
import com.abcfitness.ignite.entity.ClubClass;
import com.abcfitness.ignite.entity.Member;
import com.abcfitness.ignite.exceptions.BusinessException;
import com.abcfitness.ignite.repository.BookingRepository;
import com.abcfitness.ignite.repository.ClubClassRepository;
import com.abcfitness.ignite.repository.MemberRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService implements BookingServiceI {

    private final BookingRepository bookingRepository;
    private final MemberRepository memberRepository;
    private final ClubClassRepository clubClassRepository;
    private final EntityManager entityManager;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(ClassBookingRequestDTO classBookingRequestDTO) {
        // check if its not future date
        if (!isAfterToday(classBookingRequestDTO.getBookingDate()))
            throw BusinessException.builder().message("Slots can be only booked for future dates")
                    .build();

        // check if there are enough slots for the day
        ClubClass clubClass = clubClassRepository.findById(classBookingRequestDTO.getClassId())
                .orElseThrow(() -> BusinessException.builder().message("class doesn't exists").build());

        // check if specified date is in bounds with class date
        if (!isDateBetween(classBookingRequestDTO.getBookingDate(), clubClass.getStartDate(), clubClass.getEndDate()))
            throw BusinessException.builder().message("There are no slots for selected class on the selected date")
                    .build();

        Member member = memberRepository.findById(classBookingRequestDTO.getMemberId())
                .orElseThrow(() -> BusinessException.builder().message("Member doesn't exists").build());
        Integer numberOfBookingsByClassAndDate = bookingRepository.numberOfBookingsByClassAndDate(
                classBookingRequestDTO.getClassId(), classBookingRequestDTO.getBookingDate());

        if (numberOfBookingsByClassAndDate >= clubClass.getCapacity())
            throw BusinessException.builder().message("There are no slots available for booking").build();

        Booking booking = Booking.builder()
                .member(member)
                .clubClass(clubClass)
                .bookingDate(classBookingRequestDTO.getBookingDate())
                .build();
        bookingRepository.save(booking);
        return booking;
    }

    public boolean isDateBetween(Date targetDate, Date startDate, Date endDate) {
        return (targetDate.compareTo(startDate) >= 0) && (targetDate.compareTo(endDate) <= 0);
    }

    public boolean isAfterToday(Date date) {
        LocalDate now = LocalDate.now();
        return now.isBefore(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    @Override
    public List<BookingQueryResponseDTO> getBookings(Long ownerId, Date startDate, Date endDate, String memberName) {
        StringBuilder queryStringBuilder = new StringBuilder(
                "select new com.abcfitness.ignite.dto.BookingQueryResponseDTO(cc.className, b.bookingDate, cc.startTime,m.name) from Booking b join Member m on b.member.id=m.id join ClubClass cc on b.clubClass.id=cc.id join Club c on c.id=cc.club.id join Owner o on c.owner.id=o.id where o.id=:ownerId ");

        List<String> conditions = new ArrayList<>();
        if (startDate != null) {
            conditions.add("FUNCTION('DATE',b.bookingDate)>=FUNCTION('DATE',:startDate)");
        }
        if (endDate != null) {
            conditions.add("FUNCTION('DATE',b.bookingDate)<=FUNCTION('DATE',:endDate)");
        }
        if (memberName != null && !memberName.isEmpty()) {
            conditions.add("m.name LIKE CONCAT('%',:memberName,'%')");
        }
        if (conditions.size() > 0) {
            queryStringBuilder.append(" AND " + String.join(" AND ", conditions));
        }
        TypedQuery<BookingQueryResponseDTO> query = entityManager.createQuery(queryStringBuilder.toString(),
                BookingQueryResponseDTO.class);

        query.setParameter("ownerId", ownerId);
        if (startDate != null) {
            query.setParameter("startDate", startDate);
        }
        if (endDate != null) {
            query.setParameter("endDate", endDate);
        }
        if (memberName != null && !memberName.isEmpty()) {
            query.setParameter("memberName", memberName);
        }
        return query.getResultList();
    }

}
