package com.dbwp031.dylc.repository;

import com.dbwp031.dylc.domain.Member;
import com.dbwp031.dylc.domain.Todo;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void member생성() {
        memberRepository.deleteAll();
        String name = "test_name";
        String email = "test@example.com";
        String picture = "test_picture";
        String nickname = "test_nickname";

        memberRepository.save(Member.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .nickname(nickname).build());
    }

    @AfterEach
    public void cleanup() {
        memberRepository.deleteAll();
        todoRepository.deleteAll();
    }

    @Test
    public void Todo저장_불러오기_project_X() {
        //given

        String content = "test_content";
        Boolean done = false;

        List<Member> memberList = memberRepository.findAll();
        Member member = memberList.get(0);

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
}
