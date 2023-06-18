//package com.dbwp031.dylc.domain.eventListener;
//
//import com.dbwp031.dylc.domain.FriendRequest;
//import com.dbwp031.dylc.domain.FriendRequestStatus;
//import com.dbwp031.dylc.domain.Friendship;
//import com.dbwp031.dylc.repository.FriendRequestRepository;
//import com.dbwp031.dylc.repository.FriendshipRepository;
//import jakarta.persistence.EntityListeners;
//import jakarta.persistence.PrePersist;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//import jakarta.persistence.PostUpdate;
//@NoArgsConstructor
//@Component
//public class FriendRequestListener {
//    @PrePersist
//    public void logTest(Object o) {
////        sout("logTest")
//        System.out.println("logTest");
//
//    }
//
////    private FriendshipRepository friendshipRepository;
////
////    @Autowired
////    public FriendRequestListener(FriendshipRepository friendshipRepository) {
////        this.friendshipRepository = friendshipRepository;
////        System.out.println("HIHIHIHIHIHIHIHIHIHIHIHI");
////    }
//
//    @PostUpdate
//    public void postUpdate(Object o) {
//        System.out.println("!!!!!!!!!!PostUpdate");
//        if (o instanceof FriendRequest friendRequest) {
//            if (friendRequest.getRequestStatus() == FriendRequestStatus.ACCEPTED) {
//                Friendship friendship = Friendship.builder()
//                        .member1(friendRequest.getRequester())
//                        .member2(friendRequest.getRequestedMember())
//                        .build();
//                friendshipRepository.save(friendship);
//            }
//        }
//    }
//    @PostUpdate
//    public void postUpdate(Object o) {
//        System.out.println("post update!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//
//    }
//}
