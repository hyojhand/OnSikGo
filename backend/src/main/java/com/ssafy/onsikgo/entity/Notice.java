package com.ssafy.onsikgo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@RequiredArgsConstructor
public class Notice {

    @Id @GeneratedValue
    private Long noticeId;

    @Column(nullable = false)
    private String content; // 알림 내용
    @Column(nullable = false)
    private Boolean state; // 알림의 상태 [읽음, 안읽음]

    private String location; // 알림 클릭시 이동할 위치

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;
}

