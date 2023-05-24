package com.dbwp031.dylc.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Todo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="todo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;


    @Column(nullable=false)
    private String content;

    @Column(nullable=false)
    private boolean done;
    @Builder
    public Todo(Member member, Project project, String content, boolean done) {
        this.member = member;
        this.project = project;
        this.content = content;
        this.done = done;
    }
}
