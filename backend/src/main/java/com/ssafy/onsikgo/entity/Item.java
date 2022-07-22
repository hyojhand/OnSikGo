package com.ssafy.onsikgo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@RequiredArgsConstructor
public class Item {

    @Id @GeneratedValue
    private Long itemId;

    @Column(nullable = false)
    private String itemName; // 상품명

    @Column(nullable = false)
    private int price; // 상품가격

    private String itemUrl; // 상품 이미지

    private String comment; // 상품 설명(코멘트)

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

    @OneToMany(mappedBy = "item")
    private List<SaleItem> saleItems = new ArrayList<>();

}
