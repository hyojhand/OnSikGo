package com.ssafy.onsikgo.dto;

import lombok.*;

@Getter
@Setter
 @AllArgsConstructor
@NoArgsConstructor
public class PageDto {

    String keyword;
    Integer page;
    Integer size;
}
