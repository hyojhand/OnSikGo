package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.StoreDto;
import com.ssafy.onsikgo.entity.Follow;
import com.ssafy.onsikgo.entity.Store;
import com.ssafy.onsikgo.entity.User;
import com.ssafy.onsikgo.repository.FollowRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final TokenProvider tokenProvider;
    @Transactional
    public ResponseEntity<String>register(HttpServletRequest request, Long store_id){
        String token = request.getHeader("access-token");
        User findUser = null;
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }
        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));

        findUser = userRepository.findByEmail(userEmail).get();

        Long storeId = store_id;
        Store findStore = storeRepository.findById(storeId).get();

        Follow follow = Follow.builder()
                        .store(findStore)
                        .user(findUser)
                        .build();
        log.info(follow.toString());
        followRepository.save(follow);
        return new ResponseEntity<>("팔로잉에 성공했습니다.", HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<Object> getFollowList(HttpServletRequest request){
        String token = request.getHeader("access-token");
        User findUser = null;
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }
        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));

        findUser = userRepository.findByEmail(userEmail).get();

        List<Follow> follows = followRepository.findByUser(findUser);
        List<StoreDto> result = new ArrayList<>();
        for(Follow follow:follows){
            result.add(follow.getStore().toDto());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<Object>delete(HttpServletRequest request, Long store_id){
        String token = request.getHeader("access-token");
        User findUser = null;
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }
        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));

        findUser = userRepository.findByEmail(userEmail).get();

        Store findStore = storeRepository.findById(store_id).get();

        Follow follow = followRepository.findFollowByUserAndStore(findUser,findStore);

        followRepository.delete(follow);

        return new ResponseEntity<>("팔로우 삭제", HttpStatus.OK);
    }
}
