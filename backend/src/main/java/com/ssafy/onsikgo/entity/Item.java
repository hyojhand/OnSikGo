package com.ssafy.onsikgo.entity;

import com.ssafy.onsikgo.dto.ItemDto;
import com.ssafy.onsikgo.dto.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Item {

    @Id @GeneratedValue
    private Long itemId;

    @Column(nullable = false)
    private String itemName; // 상품명

    @Column(nullable = false)
    private Integer price; // 상품가격

    private String itemImgUrl; // 상품 이미지

    private String comment; // 상품 설명(코멘트)

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
    private List<SaleItem> saleItems = new ArrayList<>();

    public void addStore(Store store) {
        this.store = store;
        return;
    }

    public ItemDto toDto() {
        return ItemDto.builder()
                .itemId(this.itemId)
                .itemName(this.itemName)
                .price(this.price)
                .itemImgUrl(this.itemImgUrl)
                .comment(this.comment)
                .build();
    }

    public Item update(ItemDto itemDto) {
        this.itemName = itemDto.getItemName();
        this.price = itemDto.getPrice();
        this.itemImgUrl = itemDto.getItemImgUrl();
        this.comment = itemDto.getComment();
        return this;
    }
}
