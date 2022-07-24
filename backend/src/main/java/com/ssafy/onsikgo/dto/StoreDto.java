package com.ssafy.onsikgo.dto;

import com.ssafy.onsikgo.entity.Category;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {

    private String storeName;
    private String location;
    private String tel;
    private String storeNum;
    private String openDate;
    private String ownerName;
    private String storeImgUrl;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private Category category;
}
