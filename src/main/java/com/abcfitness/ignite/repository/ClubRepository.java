package com.abcfitness.ignite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abcfitness.ignite.entity.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {
    
}
