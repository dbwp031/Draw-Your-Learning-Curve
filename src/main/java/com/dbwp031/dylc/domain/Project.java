package com.dbwp031.dylc.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class Project extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "project")
    private List<Todo> todos = new ArrayList<Todo>();

    @Column(nullable = false)
    private String title;

    @Builder
    public Project(Member member, String title) {
        this.member = member;
        this.title = title;
    }
}
