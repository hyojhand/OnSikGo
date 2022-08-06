package com.ssafy.onsikgo.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class HistoryResDto {
    int total;// 총 등록 재고
    int sold;// 재고 중 판매된 재고
    int left;// total-sold
    int revenue; //수익 = sold * 할인가

    public HistoryResDto(){
        this.total=0;
        this.sold=0;
        this.left=0;
        this.revenue=0;
    }

    public void update(int total,int sold,int left,int revenue){
        this.total+=total;
        this.sold+=sold;
        this.left+=left;
        this.revenue+=revenue;
    }
}
