package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Follow;
import com.ssafy.onsikgo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByUser(User user);
}
