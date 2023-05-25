package com.dbwp031.dylc.domain;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db에 위임.
    private Long id;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Todo> todos = new ArrayList<Todo>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects = new ArrayList<Project>();

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
