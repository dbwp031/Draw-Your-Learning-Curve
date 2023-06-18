package com.dbwp031.dylc.controller;

import com.dbwp031.dylc.domain.FriendRequestStatus;
import com.dbwp031.dylc.domain.FriendRequest;
import com.dbwp031.dylc.domain.Friendship;
import com.dbwp031.dylc.domain.Member;
import com.dbwp031.dylc.repository.FriendRequestRepository;
import com.dbwp031.dylc.repository.FriendshipRepository;
import com.dbwp031.dylc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/friend")
public class FriendController {
    private final MemberRepository memberRepository;
    private final FriendshipRepository friendshipRepository;
    private final FriendRequestRepository friendRequestRepository;

    @GetMapping("")
    public String friendPage(Model model, Principal principal) {
        Optional<Member> memberOptional = memberRepository.findByLoginId(principal.getName());
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            List<Friendship> friendshipList = friendshipRepository.findAllByMember1IdOrMember2Id(member.getId(), member.getId());
            List<FriendRequest> sentFriendRequestList = friendRequestRepository.findAllByRequester(member);
            List<FriendRequest> postedFriendRequestList = friendRequestRepository.findAllByRequestedMemberAndRequestStatus(member, FriendRequestStatus.PENDING);

//            Map<String, FriendRequestStatus> friendRequestDtoList = new HashMap<>();
            //이렇게 말고 그냥 얘는 dto 만들어서 해결해야 할듯
            List<Member> friendList = new ArrayList<>();
            for (Friendship friendship : friendshipList) {
                if (friendship.getMember1().getId().equals(member.getId())) {
                    friendList.add(friendship.getMember2());
                } else {
                    friendList.add(friendship.getMember1());
                }
            }

            model.addAttribute("friendList", friendList);
            model.addAttribute("sentFriendRequestList", sentFriendRequestList);
            model.addAttribute("postedFriendRequestList", postedFriendRequestList);

        }
        return "template/friend/main";
    }

    @GetMapping("/request/create")
    public String friendCreate() {
        return "template/friend/request_create";
    }

    @PostMapping("/request/create")
    public String friendCreatePost(Model model, @RequestParam String searchBy, @RequestParam String enterValue) {
//        System.out.println(searchBy);
//        System.out.println(enterValue);
        List<Member> searchedMember;
        if (searchBy.equals("name")) {
            searchedMember = memberRepository.findByNameLikeIgnoreCase(enterValue);
        } else if (searchBy.equals("loginId")) {
            searchedMember = memberRepository.findByLoginIdLikeIgnoreCase(enterValue);
        } else {
            searchedMember = new ArrayList<>();
            System.out.println("searchBy error");
        }
        model.addAttribute("searchedMember", searchedMember);
        return "template/friend/request_create";
    }

    @PostMapping("/request/member")
    public String request(Principal principal, @RequestParam String requestedMemberLoginId) {
        String requesterName = principal.getName();
        Optional<Member> requester = memberRepository.findMemberByLoginId(requesterName);
        Optional<Member> requestedMember = memberRepository.findByLoginId(requestedMemberLoginId);

        FriendRequest friendRequest = FriendRequest.builder()
                .requester(requester.get())
                .requestedMember(requestedMember.get())
                .requestStatus(FriendRequestStatus.PENDING)
                .build();
        friendRequestRepository.save(friendRequest);
        return "redirect:/friend";
    }
}
