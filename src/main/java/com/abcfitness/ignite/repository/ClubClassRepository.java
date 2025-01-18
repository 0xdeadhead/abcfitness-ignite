package com.abcfitness.ignite.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abcfitness.ignite.entity.ClubClass;

@Repository
public interface ClubClassRepository extends JpaRepository<ClubClass, Long> {

    @Query("select count(cc)>0 from ClubClass cc join Club c on cc.club.id=c.id  where c.id=:clubId and (( :startDate>=cc.startDate and :startDate<=cc.endDate ) or ( :endDate>=cc.startDate and :endDate<=cc.endDate))")
    public Boolean classesExistsInGivenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
            @Param("clubId") Long clubId);

    

}
