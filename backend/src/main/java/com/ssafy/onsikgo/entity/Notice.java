package com.ssafy.onsikgo.entity;

import com.ssafy.onsikgo.dto.NoticeDto;
import com.ssafy.onsikgo.dto.OrderDto;
import com.ssafy.onsikgo.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String content;
    @Column(nullable = false)
    private Boolean state; // 알림의 상태 [읽음, 안읽음]

    @Column(nullable = false)
    private Long receivedId;

    @Enumerated(EnumType.STRING)
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
    public void setstate(){
        this.state=true;
    }
    public NoticeDto toDto(UserDto userDto, OrderDto orderDto) {
        return NoticeDto.builder()
                .content(this.content)
                .state(this.state)
                .receivedId(this.receivedId)
                .userDto(userDto)
                .orderDto(orderDto)
                .noticeState(this.noticeState)
                .build();
    }

}

