package com.dbwp031.dylc.repository;

import com.dbwp031.dylc.domain.FriendRequest;
import com.dbwp031.dylc.domain.FriendRequestStatus;
import com.dbwp031.dylc.domain.Member;
import com.dbwp031.dylc.domain.Project;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    public List<FriendRequest> findAllByRequester(Member member);
    public List<FriendRequest> findAllByRequestedMember(Member member);
    public List<FriendRequest> findAllByRequestedMemberAndRequestStatus(Member member, FriendRequestStatus friendRequestStatus);

    @Transactional
    @Modifying
    @Query("update FriendRequest f set f.requestStatus=?2 where f.id=?1")
    public void updateRequestStatus(Long id, FriendRequestStatus friendRequestStatus);
}
