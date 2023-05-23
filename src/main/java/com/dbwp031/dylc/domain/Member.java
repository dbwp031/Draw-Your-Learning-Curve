package com.dbwp031.dylc.domain;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db에 위임.
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable=false)
    private String email;

    @Column(nullable = false)
    private String picture;

    @Column(length=50, nullable = false)
    private String nickname;

    @Builder
    public Member(String name, String email, String picture, String nickname) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.nickname = nickname;
    }
}
