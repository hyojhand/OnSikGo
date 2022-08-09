package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.NoticeDto;
import com.ssafy.onsikgo.dto.OrderDto;
import com.ssafy.onsikgo.dto.SaleItemDto;
import com.ssafy.onsikgo.dto.UserDto;
import com.ssafy.onsikgo.entity.*;
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

    @Transactional
    public ResponseEntity<List<NoticeDto>> getList(HttpServletRequest request) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if (!findUser.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        List<Notice> notices = noticeRepository.findByReceivedId(findUser.get().getUserId());
        List<NoticeDto> noticeDtos = new ArrayList<>();
        UserDto userDto = findUser.get().toDto();
        for (Notice notice : notices) {
            notice.setstate();
            noticeRepository.save(notice);
            Order order = notice.getOrder();
            SaleItem saleItem = order.getSaleItem();
            Item item = saleItem.getItem();
            Sale sale = saleItem.getSale();
            Store store = sale.getStore();
            SaleItemDto saleItemDto = saleItem.toDto(item.toDto(), sale.toDto(store.toDto()));
            OrderDto orderDto = order.toDto(saleItemDto);
            noticeDtos.add(notice.toDto(userDto, orderDto));
        }

        return new ResponseEntity<>(noticeDtos, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> delete(Long notice_id) {

        Optional<Notice> findNotice = noticeRepository.findById(notice_id);
        Notice notice = findNotice.get();
        noticeRepository.delete(notice);
        return new ResponseEntity<>("알림이 삭제되었습니다.", HttpStatus.OK);
    }

    public ResponseEntity<Boolean> stateFalseCheck(HttpServletRequest request){
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if (!findUser.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        List<Notice> notices = noticeRepository.findByReceivedId(findUser.get().getUserId());

        for (Notice notice : notices) {
            if(!notice.getState()){
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}

