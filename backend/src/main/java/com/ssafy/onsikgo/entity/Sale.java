package com.ssafy.onsikgo.entity;

import com.ssafy.onsikgo.dto.SaleDto;
import com.ssafy.onsikgo.dto.StoreDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long saleId;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private Boolean closed;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

    @OneToMany(mappedBy = "sale" , cascade = CascadeType.REMOVE)
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
