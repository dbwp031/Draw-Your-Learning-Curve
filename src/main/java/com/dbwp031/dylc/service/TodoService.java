package com.dbwp031.dylc.service;

import com.dbwp031.dylc.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    
}
