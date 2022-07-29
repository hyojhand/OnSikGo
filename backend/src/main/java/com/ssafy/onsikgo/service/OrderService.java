package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.NoticeDto;
import com.ssafy.onsikgo.dto.OrderDto;
import com.ssafy.onsikgo.entity.*;
import com.ssafy.onsikgo.repository.NoticeRepository;
import com.ssafy.onsikgo.repository.OrderRepository;
import com.ssafy.onsikgo.repository.SaleItemRepository;
import com.ssafy.onsikgo.repository.UserRepository;
import com.ssafy.onsikgo.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SaleItemRepository saleItemRepository;
    private final NoticeRepository noticeRepository;
    private final TokenProvider tokenProvider;

//    private final EntityManager em;

    @Transactional
    public ResponseEntity<String> order(OrderDto orderDto, HttpServletRequest request) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if(!findUser.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 유저", HttpStatus.NO_CONTENT);
        }

        Long saleItemId = orderDto.getSaleItemId();
        Optional<SaleItem> findSaleItem = saleItemRepository.findById(saleItemId);
        if(!findSaleItem.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 판매상품", HttpStatus.NO_CONTENT);
        }

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String date = today.format(formatter);
        orderDto.setDate(date);

        Order order = orderDto.toEntity(findUser.get(), findSaleItem.get());
        orderRepository.save(order);

//        em.flush();

        String content = findUser.get().getNickname() + "님의 주문이 도착했습니다.";
        SaleItem saleItem = order.getSaleItem();
        Sale sale = saleItem.getSale();
        Store store = sale.getStore();
        User storeUser = store.getUser();

        Notice notice = new Notice(content, findUser.get(), order, storeUser.getUserId());
        noticeRepository.save(notice);

        return new ResponseEntity<>("주문이 등록되었습니다.", HttpStatus.OK);
    }

    public ResponseEntity<List<OrderDto>> getList(HttpServletRequest request) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if(!findUser.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        List<Order> orders = orderRepository.findByUser(findUser.get());
        List<OrderDto> orderDtos = new ArrayList<>();
        for(Order order : orders) {
            SaleItem saleItem = order.getSaleItem();
            Sale sale = saleItem.getSale();
            Item item = saleItem.getItem();
            Store store = sale.getStore();
            OrderDto orderDto = order.toDto(saleItem.toDto(item.toDto(),sale.toDto(store.toDto())));
            orderDtos.add(orderDto);
        }

        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> signOrder(String order_id, HttpServletRequest request) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if(!findUser.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 유저", HttpStatus.NO_CONTENT);
        }

        Optional<Order> findOrder = orderRepository.findById(Long.valueOf(order_id));
        if(!findOrder.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 주문", HttpStatus.NO_CONTENT);
        }

        findOrder.get().update(State.ORDER);
        orderRepository.save(findOrder.get());

        SaleItem saleItem = findOrder.get().getSaleItem();
        int count = saleItem.getStock() - findOrder.get().getCount();
        saleItem.update(count);
        saleItemRepository.save(saleItem);


        String content = findUser.get().getNickname() + "님의 상품이 준비되었습니다.";
        Notice notice = new Notice(content, findUser.get(), findOrder.get(), findOrder.get().getUser().getUserId());
        noticeRepository.save(notice);

        return new ResponseEntity<>("주문이 승인되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> refuseOrder(HashMap<String, String> map, String order_id,HttpServletRequest request) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if(!findUser.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 유저", HttpStatus.NO_CONTENT);
        }

        Optional<Order> findOrder = orderRepository.findById(Long.valueOf(order_id));
        if(!findOrder.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 주문", HttpStatus.NO_CONTENT);
        }

        findOrder.get().update(State.CANCEL);
        orderRepository.save(findOrder.get());

        String reason = map.get("reason");
        String content = findOrder.get().getUser().getNickname() + "님의 상품이 " + reason + "의 이유로 " + "취소되었습니다.";
        Notice notice = new Notice(content, findUser.get(), findOrder.get(), findOrder.get().getUser().getUserId());
        noticeRepository.save(notice);

        return new ResponseEntity<>("가게사정으로 주문이 거절되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> cancelOrder(String order_id,HttpServletRequest request) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if(!findUser.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 유저", HttpStatus.NO_CONTENT);
        }

        Optional<Order> findOrder = orderRepository.findById(Long.valueOf(order_id));
        if(!findOrder.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 주문", HttpStatus.NO_CONTENT);
        }

        findOrder.get().update(State.CANCEL);
        orderRepository.save(findOrder.get());

        SaleItem saleItem = findOrder.get().getSaleItem();
        Sale sale = saleItem.getSale();
        Store store = sale.getStore();
        User storeUser = store.getUser();

        String content = findUser.get().getNickname() + "님이 주문을 취소했습니다.";
        Notice notice = new Notice(content, findUser.get(), findOrder.get(), storeUser.getUserId());
        noticeRepository.save(notice);

        return new ResponseEntity<>("사용자가 주문을 취소하였습니다.", HttpStatus.OK);
    }

}
