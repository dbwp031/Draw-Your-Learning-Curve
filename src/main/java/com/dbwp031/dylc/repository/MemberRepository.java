package com.dbwp031.dylc.repository;

import com.dbwp031.dylc.domain.Member;
import com.dbwp031.dylc.domain.Project;
import com.dbwp031.dylc.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findMemberByLoginId(String loginId);
}
