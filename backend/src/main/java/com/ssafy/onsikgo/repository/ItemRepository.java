package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Item;
import com.ssafy.onsikgo.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByStoreAndItemNameContaining(Store store, String keyword);
    Page<Item> findByStore(Store store, Pageable pageable);
    Page<Item> findPageByStoreAndItemNameContaining(Store store, String keyword, Pageable pageable);
}
