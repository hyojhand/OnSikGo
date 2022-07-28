package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findByReceivedId(Long received_id);
}
