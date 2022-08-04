package com.ssafy.onsikgo.controller;


import com.ssafy.onsikgo.dto.ReviewDto;
import com.ssafy.onsikgo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> register(HttpServletRequest request, @RequestBody HashMap<String, String> map) {
        return reviewService.register(request, map);
    }
    @GetMapping("/user")
    public ResponseEntity<Object> getUserReview(HttpServletRequest request){
        return reviewService.getUserReview(request);
    }

    @PostMapping("/user")
    public ResponseEntity<List<ReviewDto>> getUserNicknameReview(@RequestBody HashMap<String,String> map){
        return reviewService.getUserNicknameReview(map);
    }

    @GetMapping("/store/{store_id}")
    public ResponseEntity<List<ReviewDto>> getStoreReview(@PathVariable Long store_id){
        return reviewService.getStoreReview(store_id);
    }
    @DeleteMapping("/{review_id}")
    public ResponseEntity<String> delete(@PathVariable Long review_id){
        return reviewService.delete(review_id);
    }
    @PatchMapping("/{review_id}")
    public ResponseEntity<String> report(@PathVariable Long review_id){
        return reviewService.report(review_id);
    }

    @GetMapping("/review/alllist")
    public ResponseEntity<List<ReviewDto>> getAllList() {
        return reviewService.getAllList();
    }
}
