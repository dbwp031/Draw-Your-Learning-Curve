package com.dbwp031.dylc.repository;

import com.dbwp031.dylc.domain.Member;
import com.dbwp031.dylc.domain.Project;
import com.dbwp031.dylc.domain.Todo;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProjectRepository projectRepository;

    public Member injectMember() {
        String name = "test_name";
        String email = "test@example.com";
        String picture = "test_picture";
        String nickname = "test_nickname";


        Member member = Member.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .nickname(nickname).build();

        memberRepository.save(member);

        return member;
    }


    public Project injectProject(Member member) {
        String title = "test_title";
        Project project = Project.builder()
                .member(member)
                .title(title)
                .build();
        projectRepository.save(project);

        return project;
    }


    @Test
    public void Todo저장_불러오기_project_X() {
        //given

        String content = "test_content";
        Boolean done = false;

        Member member = injectMember();

        todoRepository.save(Todo.builder()
                .member(member)
                .content(content)
                .done(done).build());

        //when
        List<Todo> todoList = todoRepository.findAll();

        //then

        Todo todo = todoList.get(0);
        assertThat(todo.getMember().getId()).isEqualTo(member.getId());
        assertThat(todo.getContent()).isEqualTo((content));
        assertThat(todo.getProject()).isEqualTo(null);
        assertThat(todo.isDone()).isEqualTo(done);
    }

    @Test
    public void Todo저장_불러오기_project_O() {
        //given

        String content = "test_content";
        Boolean done = false;

        Member member = injectMember();
        Project project = injectProject(member);

        todoRepository.save(Todo.builder()
                .member(member)
                .content(content)
                .done(done)
                .project(project)
                .build());

        //when
        List<Todo> todoList = todoRepository.findAll();

        //then

        Todo todo = todoList.get(0);
        assertThat(todo.getMember().getId()).isEqualTo(member.getId());
        assertThat(todo.getContent()).isEqualTo((content));
        assertThat(todo.getProject()).isEqualTo(project);
        assertThat(todo.isDone()).isEqualTo(done);
    }
}
