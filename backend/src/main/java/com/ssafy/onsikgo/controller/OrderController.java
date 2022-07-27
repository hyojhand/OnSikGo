package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.OrderDto;
import com.ssafy.onsikgo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> order(@RequestBody OrderDto orderDto, HttpServletRequest request) {
        return orderService.order(orderDto, request);
    }

    @PatchMapping("/sign/{order_id}")
    public ResponseEntity<String> signOrder(@PathVariable Long order_id) {
        return orderService.signOrder(order_id);
    }

    @PatchMapping("/refuse/{order_id}")
    public ResponseEntity<String> refuseOrder(@RequestBody HashMap<String, String> map, @PathVariable Long order_id) {
        return orderService.refuseOrder(map, order_id);
    }

    @PatchMapping("/cancel/{order_id}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long order_id) {
        return orderService.cancelOrder(order_id);
    }

}
