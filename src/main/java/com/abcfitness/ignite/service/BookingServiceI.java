package com.abcfitness.ignite.service;

import java.util.Date;
import java.util.List;

import com.abcfitness.ignite.dto.BookingQueryResponseDTO;
import com.abcfitness.ignite.dto.ClassBookingRequestDTO;
import com.abcfitness.ignite.entity.Booking;

public interface BookingServiceI {

    public Booking createBooking(ClassBookingRequestDTO classBookingRequestDTO);

    public List<BookingQueryResponseDTO> getBookings(Long ownerId, Date startDate, Date endDate, String memberName);

}
