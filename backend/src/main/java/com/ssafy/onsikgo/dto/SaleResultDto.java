package com.ssafy.onsikgo.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleResultDto {

    private SaleDto saleDto;
    private List<SaleItemDto> saleItemDtoList;
}
