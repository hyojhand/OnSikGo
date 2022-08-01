package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Item;
import com.ssafy.onsikgo.entity.Sale;
import com.ssafy.onsikgo.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

    List<SaleItem> findBySale(Sale sale);
    Optional<SaleItem> findByItem(Item item);
}
