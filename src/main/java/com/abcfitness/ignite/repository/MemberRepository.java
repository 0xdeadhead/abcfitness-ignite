package com.abcfitness.ignite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.abcfitness.ignite.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
