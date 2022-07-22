package com.ssafy.onsikgo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@RequiredArgsConstructor
public class Sale {

    @Id @GeneratedValue
    private Long saleId;

    @Column(nullable = false)
    private int totalPricel; // 총 판매액

    @Column(nullable = false)
    private Date date; // 날짜

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "storeId")
    private Store store;


}
