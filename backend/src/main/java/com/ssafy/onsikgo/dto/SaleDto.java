package com.ssafy.onsikgo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto {

    private Long saleId;
    private StoreDto storeDto;
    private Integer totalPrice;
    private String date;
    private Boolean closed;
}
