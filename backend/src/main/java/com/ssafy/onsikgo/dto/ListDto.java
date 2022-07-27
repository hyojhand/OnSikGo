package com.ssafy.onsikgo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListDto {

    private String keyword;
    private String category;
}
