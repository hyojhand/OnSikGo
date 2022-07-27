package com.ssafy.onsikgo.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue
    private Long orderId;

    @Column(nullable = false)
    private String date; // 주문 날짜

    @Column(nullable = false)
    private int count; // 수량

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state; // 주문 상태 [주문대기 WAIT, 승인완료 SUCCESS, 승인거절 FAIL]

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "saleItemId")
    private SaleItem saleItem;

    public Order update(State state) {
        this.state = state;
        return this;
    }
}
