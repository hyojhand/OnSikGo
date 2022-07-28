package com.ssafy.onsikgo.entity;

import com.ssafy.onsikgo.dto.ReviewDto;
import com.ssafy.onsikgo.dto.SaleDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id @GeneratedValue
    private Long reviewId;

    @Column(nullable = false)
    private String content; // 내용

    @CreatedDate
    @Column(nullable = false)
    private String createdDate; // 작성일

    @Column(nullable = false)
    private Boolean reported; // 신고여부

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

    public void update(){
        this.reported = true;
    }

    public ReviewDto toDto(){
        return ReviewDto.builder()
                .content(this.content)
                .createdDate(this.createdDate)
                .reported(this.reported)
                .storeDto(this.store.toDto())
                .nickName(this.user.getNickname())
                .build();
    }
}
