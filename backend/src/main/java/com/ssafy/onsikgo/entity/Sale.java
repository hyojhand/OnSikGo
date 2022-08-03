package com.ssafy.onsikgo.entity;

import com.ssafy.onsikgo.dto.SaleDto;
import com.ssafy.onsikgo.dto.StoreDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    @Id @GeneratedValue
    private Long saleId;

    @Column(nullable = false)
    private Integer totalPrice; // 총 판매액

    @Column(nullable = false)
    private String date; // 날짜

    @Column(nullable = false)
    private Boolean closed; // 영업종료여부

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

    @OneToMany(mappedBy = "sale")
    private List<SaleItem> saleItems = new ArrayList<>();

    public SaleDto toDto(StoreDto storeDto) {
        return SaleDto.builder()
                .totalPrice(this.totalPrice)
                .date(this.date)
                .closed(this.closed)
                .storeDto(storeDto)
                .saleId(this.saleId)
                .build();
    }

    public Sale updateTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Sale updateClosed() {
        this.closed = true;
        return this;
    }

}
