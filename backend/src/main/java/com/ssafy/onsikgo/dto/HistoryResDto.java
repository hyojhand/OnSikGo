package com.ssafy.onsikgo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.PriorityQueue;

@Getter
@Setter
@ToString
public class HistoryResDto {
    private PriorityQueue<SoldRatioDto> good;
    private PriorityQueue<LeftRatioDto> bad;
    private int total;

    public HistoryResDto(int total){
        this.good = new PriorityQueue<>();
        this.bad = new PriorityQueue<>();
        this.total = total;
    }
}
