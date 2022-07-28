package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
