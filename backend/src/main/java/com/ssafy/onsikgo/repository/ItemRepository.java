package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
