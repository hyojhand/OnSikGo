package com.ssafy.onsikgo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectDto {

    private String keyword;
    private String category;
}
