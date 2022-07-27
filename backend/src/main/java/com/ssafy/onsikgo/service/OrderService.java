package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.OrderDto;
import com.ssafy.onsikgo.entity.Order;
import com.ssafy.onsikgo.entity.SaleItem;
import com.ssafy.onsikgo.entity.State;
import com.ssafy.onsikgo.entity.User;
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

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SaleItemRepository saleItemRepository;
    private final TokenProvider tokenProvider;

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
        return new ResponseEntity<>("주문이 등록되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> signOrder(Long order_id) {
        Optional<Order> findOrder = orderRepository.findById(order_id);
        if(!findOrder.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 주문", HttpStatus.NO_CONTENT);
        }

        findOrder.get().update(State.ORDER);
        orderRepository.save(findOrder.get());

        SaleItem saleItem = findOrder.get().getSaleItem();
        int count = saleItem.getStock() - findOrder.get().getCount();
        saleItem.update(count);
        saleItemRepository.save(saleItem);

        return new ResponseEntity<>("주문이 승인되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> refuseOrder(HashMap<String, String> map, Long order_id) {
        Optional<Order> findOrder = orderRepository.findById(order_id);
        if(!findOrder.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 주문", HttpStatus.NO_CONTENT);
        }

        findOrder.get().update(State.CANCEL);
        orderRepository.save(findOrder.get());
        return new ResponseEntity<>("가게사정으로 주문이 거절되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> cancelOrder(Long order_id) {
        Optional<Order> findOrder = orderRepository.findById(order_id);
        if(!findOrder.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 주문", HttpStatus.NO_CONTENT);
        }

        findOrder.get().update(State.CANCEL);
        orderRepository.save(findOrder.get());
        return new ResponseEntity<>("사용자가 주문을 취소하였습니다.", HttpStatus.OK);
    }


}
