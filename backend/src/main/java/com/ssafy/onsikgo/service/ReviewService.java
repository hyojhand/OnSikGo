package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.ReviewDto;
import com.ssafy.onsikgo.entity.Review;
import com.ssafy.onsikgo.entity.Store;
import com.ssafy.onsikgo.entity.User;
import com.ssafy.onsikgo.repository.ReviewRepository;
import com.ssafy.onsikgo.repository.StoreRepository;
import com.ssafy.onsikgo.repository.UserRepository;
import com.ssafy.onsikgo.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final StoreRepository storeRepository;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    @Transactional
    public ResponseEntity<String> register(HttpServletRequest request, HashMap<String, String> map){

        String token = request.getHeader("access-token");
        User findUser = null;
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }
        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));

        findUser = userRepository.findByEmail(userEmail).get();

        Long storeId = Long.valueOf(map.get("store_id"));
        Store findStore = storeRepository.findById(storeId).get();

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String createdDate = today.format(formatter);

        String content = map.get("content");

        ReviewDto reviewDto = new ReviewDto(content,createdDate,false,findUser.getNickname(),null);

        Review review = reviewDto.toEntity(findUser,findStore);
        reviewRepository.save(review);

        return new ResponseEntity<>("리뷰가 등록되었습니다.", HttpStatus.OK);
    }

    public ResponseEntity<Object> getUserReview(HttpServletRequest request){
        String token = request.getHeader("access-token");
        User findUser = null;
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }
        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));

        findUser = userRepository.findByEmail(userEmail).get();

        List<Review> reviews = findUser.getReviews();
        List<ReviewDto> result = new ArrayList<>();
        for(Review review:reviews){
            result.add(review.toDto());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<List<ReviewDto>> getStoreReview(String store_id){
        Store findStore = storeRepository.findById(Long.valueOf(store_id)).get();

        List<Review> reviews = findStore.getReviews();
        List<ReviewDto> result = new ArrayList<>();
        for(Review review:reviews){
            result.add(review.toDto());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<String> delete(String review_id){
        Review review = reviewRepository.findById(Long.valueOf(review_id)).get();
        reviewRepository.delete(review);
        if(reviewRepository.findById(Long.valueOf(review_id)).orElse(null) != null) {
            return new ResponseEntity<>("삭제실패", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("삭제완료", HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<String> report(Long review_id){
        Review review = reviewRepository.findById(review_id).get();
        review.update();
        reviewRepository.save(review);
        return new ResponseEntity<>("리뷰가 신고되었습니다.", HttpStatus.OK);
    }
}