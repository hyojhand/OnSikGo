package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Store;
import com.ssafy.onsikgo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Long> {

    List<Store> findByStoreNameContaining(String keyword);
    List<Store> findByCategory (String category);
    List<Store> findByUser (User user);
}
