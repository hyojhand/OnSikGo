package com.ssafy.onsikgo.dto;

import com.ssafy.onsikgo.entity.Item;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long itemId;
    private String itemName;
    private Integer price;
    private String itemImgUrl;
    private String comment;

    public Item toEntity() {

        return Item.builder()
                .itemId(this.getItemId())
                .itemName(this.getItemName())
                .price(this.getPrice())
                .itemImgUrl(this.getItemImgUrl())
                .comment(this.getComment())
                .build();
    }
}
