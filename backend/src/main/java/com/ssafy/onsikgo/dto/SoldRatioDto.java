package com.ssafy.onsikgo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SoldRatioDto implements Comparable<SoldRatioDto>{
    private String name;
    private double value;
    private int sold;
    @Override
    public int compareTo(SoldRatioDto o) {
        return this.value > o.getValue() ? -1 : 1;
    }
}
