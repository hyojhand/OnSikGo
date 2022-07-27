package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Sale;
import com.ssafy.onsikgo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByStoreOrderByDateDesc(Store store);
    Sale findByStoreAndDate(Store store, String date);

}
