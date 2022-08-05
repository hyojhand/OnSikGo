package com.ssafy.onsikgo.entity;

import com.ssafy.onsikgo.dto.OrderDto;
import com.ssafy.onsikgo.dto.SaleItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "order")
    private List<Notice> notices = new ArrayList<>();

    public Order update(State state) {
        this.state = state;
        return this;
    }

    public OrderDto toDto(SaleItemDto saleItemDto) {
        return OrderDto.builder()
                .date(this.date)
                .count(this.count)
                .state(this.state)
                .saleItemDto(saleItemDto)
                .orderId(this.orderId)
                .build();
    }
}
