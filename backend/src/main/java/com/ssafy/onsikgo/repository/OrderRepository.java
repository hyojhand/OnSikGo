package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Order;
import com.ssafy.onsikgo.entity.State;
import com.ssafy.onsikgo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
    List<Order> findByUserAndDateBetweenAndState(User user, String start, String end, State state);
}
