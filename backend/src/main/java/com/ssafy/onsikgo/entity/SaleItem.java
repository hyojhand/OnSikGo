package com.ssafy.onsikgo.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class SaleItem {

    @Id @GeneratedValue
    private Long saleItemId;

    @Column(nullable = false)
    private Integer stock; // 재고

    @Column(nullable = false)
    private Integer totalStock; // 총 재고

    @Column(nullable = false)
    private Integer salePrice; // 할인가

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "saleId")
    private Sale sale;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    @OneToMany(mappedBy = "saleItem")
    private List<Order> orders = new ArrayList<>();
}
