package com.ssafy.onsikgo.dto;

import com.ssafy.onsikgo.entity.Item;
import com.ssafy.onsikgo.entity.Sale;
import com.ssafy.onsikgo.entity.SaleItem;
import lombok.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleItemDto {

    private Long saleItemId;
    private Long itemId;

    private SaleDto saleDto;
    private ItemDto itemDto;
    private Integer stock;
    private Integer totalStock;
    private Integer salePrice;

    public SaleItem toEntity(Item item,Sale sale) {

        return SaleItem.builder()
                .stock(this.stock)
                .totalStock(this.totalStock)
                .salePrice(this.salePrice)
//                .saleItemId(this.saleItemId)
                .sale(sale)
                .item(item)
                .build();
    }
}
