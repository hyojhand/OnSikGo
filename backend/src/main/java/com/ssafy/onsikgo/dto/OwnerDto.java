package com.ssafy.onsikgo.dto;

import com.ssafy.onsikgo.entity.*;
import lombok.*;

import java.util.Collections;
import java.util.HashMap;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {

    private String email;
    private String password;
    private String userName;
    private String imgUrl;
    private Role role;
    private String storeName;
    private String address;
    private String extraAddress;
    private String tel;
    private String storeNum;
    private String closingTime;
    private String offDay;
    private Category category;

    public User toUserEntity(LoginType loginType, String nickname){

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        return User.builder()
                .userName(this.getUserName())
                .password(this.getPassword())
                .email(this.getEmail())
                .imgUrl(this.getImgUrl())
                .nickname(nickname)
                .authorities(Collections.singleton(authority))
                .role(this.role)
                .loginType(loginType)
                .build();
    }


    public Store toStoreEntity(HashMap<String,String> coordinate) {

        return Store.builder()
                .storeName(this.getStoreName())
                .address(this.getAddress())
                .extraAddress(this.getExtraAddress())
                .tel(this.getTel())
                .storeNum(this.getStoreNum())
                .closingTime(this.getClosingTime())
                .offDay(this.getOffDay())
                .category(this.getCategory())
                .lat(coordinate.get("lat"))
                .lng(coordinate.get("lng"))
                .build();
    }
}
