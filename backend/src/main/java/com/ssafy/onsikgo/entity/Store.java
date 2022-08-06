package com.ssafy.onsikgo.entity;

import com.ssafy.onsikgo.dto.StoreDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long storeId;

    @Column(nullable = false)
    private String storeName; // 가게명

    @Column(nullable = false)
    private String address; // 가게 주소

    private String extraAddress;

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

    private String offDay; // 휴무일

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category; // 카테고리 [1인분, 피자, 치킨 ...]

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Sale> sales = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Follow> follows = new ArrayList<>();

    public Store update(StoreDto storeDto, HashMap<String, String> coordinate) {
        this.storeName = storeDto.getStoreName();
        this.address = storeDto.getAddress();
        this.extraAddress = storeDto.getExtraAddress();
        this.tel = storeDto.getTel();
        this.storeNum = storeDto.getStoreNum();
        this.storeImgUrl = storeDto.getStoreImgUrl();
        this.closingTime = storeDto.getClosingTime();
        this.offDay = storeDto.getOffDay();
        this.category = storeDto.getCategory();
        this.lat = coordinate.get("lat");
        this.lng = coordinate.get("lng");
        return this;
    }

    public void addUser(User user) {
        this.user = user;
        return;
    }

    public StoreDto toDto() {

        return StoreDto.builder()
                .storeId(this.storeId)
                .storeName(this.getStoreName())
                .address(this.getAddress())
                .extraAddress(this.getExtraAddress())
                .tel(this.getTel())
                .storeNum(this.getStoreNum())
                .storeImgUrl(this.getStoreImgUrl())
                .closingTime(this.getClosingTime())
                .offDay(this.getOffDay())
                .category(this.getCategory())
                .lat(this.getLat())
                .lng(this.getLng())
                .build();
    }
}
