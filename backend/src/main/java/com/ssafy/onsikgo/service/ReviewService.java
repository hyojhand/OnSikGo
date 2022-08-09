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
import java.util.Optional;

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

        Long storeId = Long.valueOf(map.get("storeId"));
        Store findStore = storeRepository.findById(storeId).get();

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String createdDate = today.format(formatter);

        String content = map.get("content");
        if(content.trim().isEmpty() || content == null) {
            return new ResponseEntity<>("리뷰내용이 없습니다.", HttpStatus.NO_CONTENT);
        }

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setContent(content);
        reviewDto.setCreatedDate(createdDate);
        reviewDto.setReported(false);
        reviewDto.setNickname(findUser.getNickname());
        reviewDto.setUserImgUrl(findUser.getImgUrl());

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

    public ResponseEntity<List<ReviewDto>> getUserNicknameReview(HashMap<String, String> map) {
        String nickName = map.get("nickname");
        Optional<User> findUser = userRepository.findByNickname(nickName);
        List<Review> reviewList = reviewRepository.findByUser(findUser.get());
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for(Review review : reviewList) {
            reviewDtoList.add(review.toDto());
        }

        return new ResponseEntity<>(reviewDtoList, HttpStatus.OK);
    }

    public ResponseEntity<List<ReviewDto>> getStoreReview(Long store_id){
        Store findStore = storeRepository.findById(store_id).get();

        List<Review> reviews = findStore.getReviews();
        List<ReviewDto> result = new ArrayList<>();
        for(Review review:reviews){
            result.add(review.toDto());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<String> delete(Long review_id){
        Review review = reviewRepository.findById(review_id).get();
        reviewRepository.delete(review);
        if(reviewRepository.findById(review_id).orElse(null) != null) {
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

    public ResponseEntity<List<ReviewDto>> getTotal() {
        List<Review> allReview = reviewRepository.findAll();
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for(Review review : allReview) {
            reviewDtoList.add(review.toDto());
        }

        return new ResponseEntity<>(reviewDtoList, HttpStatus.OK);
    }
}