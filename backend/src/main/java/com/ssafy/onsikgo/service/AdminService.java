package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.ReviewDto;
import com.ssafy.onsikgo.entity.Review;
import com.ssafy.onsikgo.entity.User;
import com.ssafy.onsikgo.repository.ReviewRepository;
import com.ssafy.onsikgo.repository.UserRepository;
import com.ssafy.onsikgo.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    @Transactional
    public ResponseEntity<String> deleteUser(HttpServletRequest request, String email) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }
        User findUser = userRepository.findByEmail(email).get();

        userRepository.delete(findUser);

        if (userRepository.findByEmail(email).orElse(null) != null) {
            return new ResponseEntity<>("삭제실패", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("삭제완료", HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<List<ReviewDto>> getReportedReviews(HttpServletRequest request) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        List<Review> reviews = reviewRepository.findByReportedIsTrue();
        List<ReviewDto> result = new ArrayList<>();
        for(Review review:reviews){
            result.add(review.toDto());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
