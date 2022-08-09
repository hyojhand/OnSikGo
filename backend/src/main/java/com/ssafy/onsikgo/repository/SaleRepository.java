package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Sale;
import com.ssafy.onsikgo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {

//    List<Sale> findByStoreOrderByDateDesc(Store store);
    Optional<Sale> findByStoreAndDate(Store store, String date);
    Optional<Sale> findByStoreAndDateAndClosedFalse(Store store, String date);
    List<Sale> findByStoreAndDateBetween(Store store, String startDate, String endDate);
}
