package com.dbwp031.dylc.repository;

import com.dbwp031.dylc.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
