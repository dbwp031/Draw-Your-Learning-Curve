package com.dbwp031.dylc.config.auth.dto;

import com.dbwp031.dylc.domain.Member;

import java.io.Serializable;

public class SessionMember implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionMember(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
    }
}
