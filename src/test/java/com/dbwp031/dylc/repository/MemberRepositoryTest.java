package com.dbwp031.dylc.repository;

import com.dbwp031.dylc.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void cleanup() {
        memberRepository.deleteAll();
    }

    @Test
    public void 멤버저장_불러오기() {
        //given
        String name = "test_name";
        String email = "test@example.com";
        String picture = "test_picture";
        String nickname = "test_nickname";

        memberRepository.save(Member.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .nickname(nickname).build());
        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        Member member = memberList.get(0);
        assertThat(member.getName()).isEqualTo(name);
        assertThat(member.getEmail()).isEqualTo(email);
        assertThat(member.getPicture()).isEqualTo(picture);
        assertThat(member.getNickname()).isEqualTo(nickname);
    }
}
