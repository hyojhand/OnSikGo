package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Item;
import com.ssafy.onsikgo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByStoreAndItemNameContaining(Store store, String itemName);
}
