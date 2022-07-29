package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.OrderDto;
import com.ssafy.onsikgo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
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

    @GetMapping
    public ResponseEntity<List<OrderDto>> getList(HttpServletRequest request) {
        return orderService.getList(request);
    }

    @PatchMapping("/sign/{order_id}")
    public ResponseEntity<String> signOrder(@PathVariable String order_id,HttpServletRequest request) {
        return orderService.signOrder(order_id,request);
    }

    @PatchMapping("/refuse/{order_id}")
    public ResponseEntity<String> refuseOrder(@RequestBody HashMap<String, String> map, @PathVariable String order_id, HttpServletRequest request) {
        return orderService.refuseOrder(map, order_id, request);
    }

    @PatchMapping("/cancel/{order_id}")
    public ResponseEntity<String> cancelOrder(@PathVariable String order_id, HttpServletRequest request) {
        return orderService.cancelOrder(order_id, request);
    }

}
