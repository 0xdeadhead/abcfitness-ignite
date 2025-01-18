package com.abcfitness.ignite.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.abcfitness.ignite.dto.ClassCreationRequestDTO;
import com.abcfitness.ignite.entity.Club;
import com.abcfitness.ignite.entity.ClubClass;
import com.abcfitness.ignite.exceptions.BusinessException;
import com.abcfitness.ignite.repository.ClubClassRepository;
import com.abcfitness.ignite.repository.ClubRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubClassService implements ClubClassServiceI {

    private final ClubRepository clubRepository;
    private final ClubClassRepository clubClassRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ClubClass createClass(ClassCreationRequestDTO classCreationRequestDTO) {

        // fetch the club details
        Club clubEntity = clubRepository.findById(classCreationRequestDTO.getClubId()).orElseThrow(
                () -> BusinessException.builder().status(404)
                        .message("Cannot find the given club id : " + classCreationRequestDTO.getClubId()).build());
        ClubClass clubClassEntity = null;

        // check if end date is future date
        if (!isAfterToday(classCreationRequestDTO.getEndDate()))
            throw BusinessException.builder().status(HttpStatus.BAD_REQUEST.value())
                    .message("End date cannot be before today").build();
        if (classCreationRequestDTO.getEndDate().compareTo(classCreationRequestDTO.getStartDate()) < 0)
            throw BusinessException.builder().status(HttpStatus.BAD_REQUEST.value())
                    .message("Start date cannot be after end date").build();

        // check if there is an existing class between specified date range
        Boolean classesExistsInGivenDates = clubClassRepository.classesExistsInGivenDates(
                classCreationRequestDTO.getStartDate(),
                classCreationRequestDTO.getEndDate(),
                classCreationRequestDTO.getClubId());
        if (!classesExistsInGivenDates) {
            clubClassEntity = mapCreationRequestToEntity(classCreationRequestDTO);
            clubClassEntity.setClub(clubEntity);
            clubClassRepository.save(clubClassEntity);
        } else {
            throw BusinessException.builder().status(400)
                    .message("Club is already occupied with other classes in given dates").build();
        }
        return clubClassEntity;
    }

    private ClubClass mapCreationRequestToEntity(ClassCreationRequestDTO classCreationRequestDTO) {

        return ClubClass.builder()
                .capacity(classCreationRequestDTO.getCapacity())
                .startDate(classCreationRequestDTO.getStartDate())
                .endDate(classCreationRequestDTO.getEndDate())
                .durationInMinutes(classCreationRequestDTO.getDurationInMinutes())
                .startTime(classCreationRequestDTO.getStartTime())
                .className(classCreationRequestDTO.getName())
                .build();

    }

    public boolean isAfterToday(Date date) {
        LocalDate now = LocalDate.now();
        return now.isBefore(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

}
