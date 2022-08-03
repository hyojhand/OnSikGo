package com.ssafy.onsikgo.dto;

import com.ssafy.onsikgo.entity.Category;
import com.ssafy.onsikgo.entity.Store;
import lombok.*;

import java.time.LocalTime;
import java.util.HashMap;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {

    private Long storeId;
    private String storeName;
    private String location;
    private String tel;
    private String storeNum;
    private String storeImgUrl;
    private String closingTime;
    private String offDay;
    private Category category;
    private String lat;
    private String lng;


    public Store toEntity(HashMap<String,String> coordinate) {

        return Store.builder()
                .storeName(this.getStoreName())
                .location(this.getLocation())
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
