package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.NoticeDto;
import com.ssafy.onsikgo.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<List<NoticeDto>> getList(HttpServletRequest request) {
        return noticeService.getList(request);
    }

    @DeleteMapping("/{notice_id}")
    public ResponseEntity<String> delete(@PathVariable Long notice_id) {
        return noticeService.delete(notice_id);
    }
}
