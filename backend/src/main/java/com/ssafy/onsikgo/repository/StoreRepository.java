package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Long> {

    List<Store> findByStoreNameContaining(String keyword);
    List<Store> findByCategory (String category);
}
