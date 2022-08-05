package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Review;
import com.ssafy.onsikgo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByUser(User user);
    List<Review> findByReportedIsTrue();
}
