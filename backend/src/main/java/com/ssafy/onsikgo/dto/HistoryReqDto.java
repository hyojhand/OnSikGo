package com.ssafy.onsikgo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HistoryReqDto {
    Long storeId;
    String endDate;
    String startDate;
}
