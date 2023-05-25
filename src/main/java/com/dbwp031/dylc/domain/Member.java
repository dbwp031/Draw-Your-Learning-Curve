package com.dbwp031.dylc.domain;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db에 위임.
    private Long id;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Todo> todos = new ArrayList<Todo>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Project> projects = new ArrayList<Project>();

    @Column(nullable = false)
    private String name;

    @Column(nullable=false)
    private String email;

    @Column(nullable = false)
    private String picture;

    @Column(length=50, nullable = false)
    private String nickname;
}
