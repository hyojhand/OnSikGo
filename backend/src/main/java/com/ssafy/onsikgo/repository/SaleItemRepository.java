package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Item;
import com.ssafy.onsikgo.entity.Sale;
import com.ssafy.onsikgo.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long>, SaleItemRepositoryCustom {

    List<SaleItem> findBySale(Sale sale);
    Optional<SaleItem> findBySaleAndItem(Sale sale, Item item);
//    List<SaleItem> findByDateAndItemNameContainingAndClosedFalse(String date, String keyword);
}
