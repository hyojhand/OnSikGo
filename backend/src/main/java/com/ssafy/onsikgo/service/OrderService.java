package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.OrderDto;
import com.ssafy.onsikgo.entity.*;
import com.ssafy.onsikgo.repository.*;
import com.ssafy.onsikgo.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final SaleRepository saleRepository;
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

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
        String time = now.format(timeFormatter);
        if(Integer.parseInt(time) < 6) {
            now = now.minusDays(1);
        }

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String date = now.format(dayFormatter);
        orderDto.setDate(date);

        Order order = orderDto.toEntity(findUser.get(), findSaleItem.get());
        orderRepository.save(order);

//        em.flush();

        String content = findUser.get().getNickname() + " 님의<br/>" + "주문이 도착했습니다.";
        SaleItem saleItem = order.getSaleItem();
        Sale sale = saleItem.getSale();
        Store store = sale.getStore();
        User storeUser = store.getUser();

        Notice notice = new Notice(content, findUser.get(), order, storeUser.getUserId(), NoticeState.ORDER);
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
    public ResponseEntity<String> signOrder(Long order_id, HttpServletRequest request) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if(!findUser.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 유저", HttpStatus.NO_CONTENT);
        }

        Optional<Order> findOrder = orderRepository.findById(order_id);
        if(!findOrder.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 주문", HttpStatus.NO_CONTENT);
        }

        findOrder.get().update(State.ORDER);
        orderRepository.save(findOrder.get());

        SaleItem saleItem = findOrder.get().getSaleItem();
        int stockCount = saleItem.getStock() - findOrder.get().getCount();
        saleItem.updateStock(stockCount);
        saleItemRepository.save(saleItem);


        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
        String time = now.format(timeFormatter);
        if(Integer.parseInt(time) < 6) {
            now = now.minusDays(1);
        }

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = now.format(dayFormatter);

        int money = saleItem.getSalePrice() * findOrder.get().getCount();
        Store store = saleItem.getItem().getStore();
        Optional<Sale> findSale = saleRepository.findByStoreAndDateAndClosedFalse(store, date);
        if(!findSale.isPresent()) {
            return new ResponseEntity<>("해당하는 날짜의 판매정보가 없습니다.", HttpStatus.NOT_FOUND);
        }

        Integer totalPrice = findSale.get().getTotalPrice();
        totalPrice += money;
        findSale.get().updateTotalPrice(totalPrice);
        saleRepository.save(findSale.get());

        String content = findUser.get().getNickname() + " 님의 " + "<br/>상품이 준비되었습니다.";
        Notice notice = new Notice(content, findUser.get(), findOrder.get(), findOrder.get().getUser().getUserId(), NoticeState.ORDER);
        noticeRepository.save(notice);

        return new ResponseEntity<>("주문이 승인되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> refuseOrder(HashMap<String, String> map, Long order_id,HttpServletRequest request) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if(!findUser.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 유저", HttpStatus.NO_CONTENT);
        }

        Optional<Order> findOrder = orderRepository.findById(order_id);
        if(!findOrder.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 주문", HttpStatus.NO_CONTENT);
        }

        findOrder.get().update(State.CANCEL);
        orderRepository.save(findOrder.get());

        String reason = map.get("reason");
        String content = findOrder.get().getUser().getNickname() + " 님의 상품이<br/>" + reason + "의 이유로 " + "취소되었습니다.";
        Notice notice = new Notice(content, findUser.get(), findOrder.get(), findOrder.get().getUser().getUserId(), NoticeState.ORDER);
        noticeRepository.save(notice);

        return new ResponseEntity<>("가게사정으로 주문이 거절되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> cancelOrder(Long order_id,HttpServletRequest request) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if(!findUser.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 유저", HttpStatus.NO_CONTENT);
        }

        Optional<Order> findOrder = orderRepository.findById(order_id);
        if(!findOrder.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 주문", HttpStatus.NO_CONTENT);
        }

        findOrder.get().update(State.CANCEL);
        orderRepository.save(findOrder.get());

        SaleItem saleItem = findOrder.get().getSaleItem();
        Sale sale = saleItem.getSale();
        Store store = sale.getStore();
        User storeUser = store.getUser();

        String content = findUser.get().getNickname() + " 님이<br/>" + "주문을 취소했습니다.";
        Notice notice = new Notice(content, findUser.get(), findOrder.get(), storeUser.getUserId(), NoticeState.ORDER);
        noticeRepository.save(notice);

        return new ResponseEntity<>("사용자가 주문을 취소하였습니다.", HttpStatus.OK);
    }

    public ResponseEntity<String> totalOrderPrice(HttpServletRequest request) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if(!findUser.isPresent()) {
            return new ResponseEntity<>("존재하지 않는 유저", HttpStatus.NO_CONTENT);
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        LocalDateTime start = now.withDayOfMonth(1);
        String startMonth = start.format(dayFormatter) + "0000";
        LocalDateTime end = now.withDayOfMonth(now.toLocalDate().lengthOfMonth());
        String endMonth = end.format(dayFormatter) + "0000";

        List<Order> findMonthOrder = orderRepository.findByUserAndDateBetweenAndState(findUser.get(), startMonth, endMonth, State.ORDER);
        int buyPrice = 0;
        for(Order order : findMonthOrder) {
            buyPrice += order.getCount() * order.getSaleItem().getSalePrice();
        }

        return new ResponseEntity<>(String.valueOf(buyPrice), HttpStatus.OK);
    }
}
