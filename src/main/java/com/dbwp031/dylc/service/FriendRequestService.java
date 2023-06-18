package com.dbwp031.dylc.service;

import com.dbwp031.dylc.domain.FriendRequest;
import com.dbwp031.dylc.domain.FriendRequestStatus;
import com.dbwp031.dylc.domain.Friendship;
import com.dbwp031.dylc.repository.FriendRequestRepository;
import com.dbwp031.dylc.repository.FriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;
    private final FriendshipRepository friendshipRepository;

    public void updateRequestStatus(Long id, String updateStatus) {
        Optional<FriendRequest> friendRequestOptional = friendRequestRepository.findById(id);
        if (friendRequestOptional.isPresent()) {
            FriendRequest friendRequest = friendRequestOptional.get();

            if (updateStatus.equals("accept")) {
                friendRequestRepository.updateRequestStatus(id, FriendRequestStatus.ACCEPTED);
                Friendship friendship = Friendship.builder()
                        .member1(friendRequest.getRequester())
                        .member2(friendRequest.getRequestedMember())
                        .build();
                friendshipRepository.save(friendship);
            } else if (updateStatus.equals("reject")) {
                friendRequestRepository.updateRequestStatus(id, FriendRequestStatus.REJECTED);
            }

        } else {
            System.out.println("updateStatus가 accept, reject가 아님");
        }
    }
}
