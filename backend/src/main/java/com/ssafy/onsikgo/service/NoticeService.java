package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.NoticeDto;
import com.ssafy.onsikgo.entity.Notice;
import com.ssafy.onsikgo.entity.Order;
import com.ssafy.onsikgo.entity.User;
import com.ssafy.onsikgo.repository.NoticeRepository;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    public ResponseEntity<List<NoticeDto>> getList(HttpServletRequest request) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if(!findUser.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        List<Notice> notices = noticeRepository.findByReceivedId(findUser.get().getUserId());
        List<NoticeDto> noticeDtos = new ArrayList<>();

        for(Notice notice : notices) {
            Order order = notice.getOrder();
        }





    }

    @Transactional
    public ResponseEntity<String> delete(Long notice_id) {

    }
}
