package com.dbwp031.dylc.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class FriendRequest extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id", insertable = false, updatable = false)
    private Member requester;

    @ManyToOne
    @JoinColumn(name="member_id", insertable = false, updatable = false)
    private Member requestedmember;

    @Column(nullable = false)
    private String requestStatus;
    @Builder
    public FriendRequest(Member requester, Member requestedmember, String requestStatus) {
        this.requester = requester;
        this.requestedmember = requestedmember;
        this.requestStatus = requestStatus;
    }
}
