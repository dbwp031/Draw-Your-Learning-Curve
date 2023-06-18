package com.dbwp031.dylc.repository;

import com.dbwp031.dylc.domain.Project;
import com.dbwp031.dylc.domain.Todo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    public Optional<Todo> findTodoById(Long id);

    public List<Todo> findAllByProject(Project project);

    @Transactional
    @Modifying
    @Query("update Todo todo set todo.done=?2 where todo.id=?1")
    public void updateDone(Long id, boolean done);

    @Transactional
    @Modifying
    @Query("update Todo todo set todo.content=?2 where todo.id=?1")
    public void updateContent(Long id, String content);


    @Transactional
    @Modifying
    @Query("update Todo todo set todo.content=?2,todo.project=?3 where todo.id=?1")
    public void updateContentAndProject(Long id, String content,Project project);
}
