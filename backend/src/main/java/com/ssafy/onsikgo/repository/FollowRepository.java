package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Follow;
import com.ssafy.onsikgo.entity.Store;
import com.ssafy.onsikgo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByUser(User user);
    Optional<Follow> findFollowByUserAndStore(User user, Store store);
}
