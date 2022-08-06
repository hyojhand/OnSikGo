package com.ssafy.onsikgo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LeftRatioDto implements Comparable<LeftRatioDto>{
    private String name;
    private double value;
    private int left;
    @Override
    public int compareTo(LeftRatioDto o) {
        return this.value > o.getValue() ? -1 : 1;
    }
}
