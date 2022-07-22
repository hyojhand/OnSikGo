package com.ssafy.onsikgo.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    private Long orderId;

    @CreatedDate
    @Column(nullable = false)
    private Date date; // 주문 날짜

    @Column(nullable = false)
    private int count; // 수량

    @Column(nullable = false)
    private State state; // 주문 상태 [주문대기 WAIT, 승인완료 SUCCESS, 승인거절 FAIL]

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "saleItemId")
    private SaleItem saleItem;
}
