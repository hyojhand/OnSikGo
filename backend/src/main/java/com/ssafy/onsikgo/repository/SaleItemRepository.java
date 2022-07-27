package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Sale;
import com.ssafy.onsikgo.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

    List<SaleItem> findBySale(Sale sale);
}
