package com.dbwp031.dylc.auth.service;

import com.dbwp031.dylc.auth.domain.MemberPrincipal;
import com.dbwp031.dylc.domain.Member;
import com.dbwp031.dylc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.Map;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    // 서드 파티 접근을 위한 access Token 까지 얻은 다음 실행된다.
    private final MemberRepository memberRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
        // 기능 1. accessToken으로 서드파티에 요청해서 사용자 정보 받아옴
        Map<String, Object> attributes = super.loadUser(oAuth2UserRequest).getAttributes();


        validateAttributes(attributes);

        Member member = registerIfNewMember(attributes);
        return MemberPrincipal.create(member, attributes);
//        https://yelimkim98.tistory.com/48
//https://yelimkim98.tistory.com/49
//        return new OAuth2User()
//        return null;
    }

    private void validateAttributes(Map<String, Object> attributes) {
        if (!attributes.containsKey("login")) {
            throw new IllegalArgumentException("서드파티에 login 아이디가 없습니다.");
        }
    }

    private Member registerIfNewMember(Map<String, Object> userInfoAttributes) {
        String loginId = (String) userInfoAttributes.get("login");
        Optional<Member> optionalMember = memberRepository.findMemberByLoginId(loginId);

        if (optionalMember.isPresent()) {
            return optionalMember.get();
        }
        Member member = Member.builder()
                .email((String) userInfoAttributes.get("email"))
                .name((String) userInfoAttributes.get("name"))
                .picture((String) userInfoAttributes.get("avatar_url"))
                .loginId((String) userInfoAttributes.get("login"))
                .build();
        return memberRepository.save(member);
    }
}
