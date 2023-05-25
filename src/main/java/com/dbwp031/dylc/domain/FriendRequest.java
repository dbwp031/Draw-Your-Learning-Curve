package com.dbwp031.dylc.domain;

import jakarta.persistence.*;
import lombok.*;
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FriendRequest extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="requester_member_id")
    private Member requester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="requested_member_id")
    private Member requestedmember;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String requestStatus;
}
