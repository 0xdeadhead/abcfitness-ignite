package com.abcfitness.ignite.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abcfitness.ignite.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("select count(b) from Booking b where b.clubClass.id=:classId and FUNCTION('DATE', b.bookingDate) = FUNCTION('DATE', :date)")
    Integer numberOfBookingsByClassAndDate(@Param("classId") Long classId, @Param("date") Date date);

 
}
