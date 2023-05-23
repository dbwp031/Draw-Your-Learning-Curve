package com.dbwp031.dylc.repository;

import com.dbwp031.dylc.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
