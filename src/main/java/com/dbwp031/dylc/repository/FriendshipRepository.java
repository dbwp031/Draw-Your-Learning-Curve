package com.dbwp031.dylc.repository;

import com.dbwp031.dylc.domain.Friendship;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findAllByMember1IdOrMember2Id(Long memberId1, Long memberId2);
}
