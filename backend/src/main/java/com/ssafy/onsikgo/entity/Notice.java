package com.ssafy.onsikgo.entity;

import lombok.*;
import org.aspectj.weaver.ast.Not;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    @Id @GeneratedValue
    private Long noticeId;

    @Column(nullable = false)
    private String content; // 알림 내용
    @Column(nullable = false)
    private Boolean state; // 알림의 상태 [읽음, 안읽음]

    private String location; // 알림 클릭시 이동할 위치

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Long receivedId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public Notice(String content, Boolean state, User user,Long receivedId, Long orderId) {
        this.content = content;
        this.state = state;
        this.user = user;
        this.receivedId = receivedId;
        this.orderId = orderId;
    }

}

