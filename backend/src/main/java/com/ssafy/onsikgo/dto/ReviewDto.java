package com.ssafy.onsikgo.dto;

import com.ssafy.onsikgo.entity.Review;
import com.ssafy.onsikgo.entity.Store;
import com.ssafy.onsikgo.entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long reviewId;
    private String content;
    private String createdDate;
    private Boolean reported;
    private String nickname;
    private StoreDto storeDto;
    private String userImgUrl;

    public Review toEntity(User user, Store store){

        return Review.builder()
                .content(this.content)
                .reported(this.reported)
                .createdDate(this.createdDate)
                .user(user)
                .store(store)
                .build();
    }
}
