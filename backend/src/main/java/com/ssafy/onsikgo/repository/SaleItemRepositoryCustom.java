package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.SaleItem;

import java.util.List;

public interface SaleItemRepositoryCustom {
    List<SaleItem> findSaleItemKeyword(String keyword,String date);
}
