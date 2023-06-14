package com.dbwp031.dylc.controller;

import com.dbwp031.dylc.domain.TodoPostDto;
import com.dbwp031.dylc.repository.TodoRepository;
import com.dbwp031.dylc.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController

public class TodoApiController {
    private final TodoRepository todoRepository;

    @PatchMapping("/api/v1/todo/checkUpdate/{id}")
    public HttpEntity<String> updateTodoCheck(@RequestBody TodoPostDto request) {
        Long id = request.getId();
        boolean done = request.isDone();

        todoRepository.updateDone(id, done);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
