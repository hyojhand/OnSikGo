package com.ssafy.onsikgo.dto;

import com.ssafy.onsikgo.entity.Category;
import com.ssafy.onsikgo.entity.Store;
import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto implements Comparable<StoreDto>{

    private Long storeId;
    private String storeName;
    private String address;
    private String extraAddress;
    private String tel;
    private String storeNum;
    private String storeImgUrl;
    private String closingTime;
    private String offDay;
    private Category category;
    private String lat;
    private String lng;
    private int totalStock;

    @Override
    public int compareTo(StoreDto o) {
        return this.totalStock > o.getTotalStock() ? -1 : 1;
    }

    public void updateTotalStock(int count){
        this.totalStock+=count;
    }

    public Store toEntity(HashMap<String,String> coordinate) {

        return Store.builder()
                .storeName(this.getStoreName())
                .address(this.getAddress())
                .extraAddress(this.getExtraAddress())
                .tel(this.getTel())
                .storeNum(this.getStoreNum())
                .storeImgUrl(this.getStoreImgUrl())
                .closingTime(this.getClosingTime())
                .offDay(this.getOffDay())
                .category(this.getCategory())
                .lat(coordinate.get("lat"))
                .lng(coordinate.get("lng"))
                .build();
    }
}
