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
    private String storeName;

    @Column(nullable = false)
    private String address;

    private String extraAddress;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String storeNum;

    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private String lng;

    private String storeImgUrl;

    @Column(nullable = false)
    private String closingTime;

    private String offDay;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

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
    public void updateImg(String imgsrc){
        this.storeImgUrl = imgsrc;
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
