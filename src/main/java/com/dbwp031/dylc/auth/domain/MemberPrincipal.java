package com.dbwp031.dylc.auth.domain;

import com.dbwp031.dylc.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
public class MemberPrincipal implements OAuth2User, UserDetails {
    private Member member;
    private List<GrantedAuthority> authorities;
    private Map<String, Object> oauthUserAttributes;

    public static MemberPrincipal create(Member member, Map<String, Object> oauthUserAttributes) {
        return new MemberPrincipal(member, List.of(() -> "ROLE_USER"), oauthUserAttributes);
    }

    public static MemberPrincipal create(Member member) {
        return new MemberPrincipal(member, List.of(() -> "ROLE_USER"), new HashMap<>());
    }
    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return String.valueOf(member.getLoginId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(oauthUserAttributes);
    }

    @Override
    public String getName() {
        return String.valueOf(member.getLoginId());
    }
}

