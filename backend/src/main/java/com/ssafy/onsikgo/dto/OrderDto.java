package com.ssafy.onsikgo.dto;

import com.ssafy.onsikgo.entity.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long saleItemId;
    private SaleItemDto saleItemDto;
    private String date;
    private Integer count;
    private State state;

    public Order toEntity(User user, SaleItem saleItem) {
        return Order.builder()
                .date(this.date)
                .count(this.count)
                .saleItem(saleItem)
                .user(user)
                .state(State.WAIT)
                .build();
    }

}
