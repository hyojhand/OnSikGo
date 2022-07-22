package com.ssafy.onsikgo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@RequiredArgsConstructor
public class Review {

    @Id @GeneratedValue
    private Long reviewId;

    @Column(nullable = false)
    private String content; // 내용

    @CreatedDate
    @Column(nullable = false)
    private Date createdDate; // 작성일

    @Column(nullable = false)
    private Boolean reported; // 신고여부

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

}
