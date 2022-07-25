package com.ssafy.onsikgo.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store {

    @Id @GeneratedValue
    private Long storeId;

    @Column(nullable = false)
    private String storeName; // 가게명

    @Column(nullable = false)
    private String location; // 가게 주소

    @Column(nullable = false)
    private String tel; // 가게 전화번호

    @Column(nullable = false)
    private String storeNum; // 사업자 등록번호

    @Column(nullable = false)
    private String lat; // 위도

    @Column(nullable = false)
    private String lng; // 경도

    private String storeImgUrl; // 가게 사진

    @Column(nullable = false)
    private String closingTime; // 가게 마감시간

    @Column(nullable = false)
    private String offDay; // 휴무일

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category; // 카테고리 [1인분, 피자, 치킨 ...]

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "store")
    private List<Sale> sales = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Follow> follows = new ArrayList<>();

    public void addUser(User user) {
        this.user = user;
        return;
    }
}
