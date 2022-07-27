package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
