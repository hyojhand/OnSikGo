package com.ssafy.onsikgo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@RequiredArgsConstructor
public class Follow {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "storeId")
    private Store store;






}
