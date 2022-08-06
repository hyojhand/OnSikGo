package com.ssafy.onsikgo.entity;

import com.ssafy.onsikgo.dto.NoticeDto;
import com.ssafy.onsikgo.dto.OrderDto;
import com.ssafy.onsikgo.dto.UserDto;
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

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long noticeId;

    @Column(nullable = false)
    private String content; // 알림 내용
    @Column(nullable = false)
    private Boolean state; // 알림의 상태 [읽음, 안읽음]

    private String location; // 알림 클릭시 이동할 위치

    @Column(nullable = false)
    private Long receivedId;

    @Column(nullable = false)
    private NoticeState noticeState;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public Notice(String content, User user, Order order, Long receivedId, NoticeState noticeState) {
        this.content = content;
        this.state = false;
        this.user = user;
        this.order = order;
        this.noticeState = noticeState;
        this.receivedId = receivedId;
    }

    public NoticeDto toDto(UserDto userDto, OrderDto orderDto) {
        return NoticeDto.builder()
                .content(this.content)
                .state(this.state)
                .location(this.location)
                .receivedId(this.receivedId)
                .userDto(userDto)
                .orderDto(orderDto)
                .noticeState(this.noticeState)
                .build();
    }

}

