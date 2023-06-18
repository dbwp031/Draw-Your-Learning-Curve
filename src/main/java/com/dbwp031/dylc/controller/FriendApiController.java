package com.dbwp031.dylc.controller;

import com.dbwp031.dylc.domain.FriendRequestStatus;
import com.dbwp031.dylc.domain.TodoPostDto;
import com.dbwp031.dylc.repository.FriendRequestRepository;
import com.dbwp031.dylc.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class FriendApiController {
    private final FriendRequestRepository friendRequestRepository;
    private final FriendRequestService friendRequestService;
    @PatchMapping("/api/v1/friend/request/{id}")
    public HttpEntity<String> updateRequestStatus(@PathVariable Long id, @RequestBody Map<String,String> requestStatusMap) {
        String requestStatus = requestStatusMap.get("requestStatus");
        friendRequestService.updateRequestStatus(id,requestStatus);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
