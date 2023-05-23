package com.dbwp031.dylc.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Friendship extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id", insertable = false, updatable = false)
    private Member member1;

    @ManyToOne
    @JoinColumn(name="member_id", insertable = false, updatable = false)
    private Member member2;

    @Builder
    public Friendship(Member member1, Member member2) {
        this.member1 = member1;
        this.member2 = member2;
    }
}
