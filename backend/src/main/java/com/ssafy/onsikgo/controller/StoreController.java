package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.StoreDto;
import com.ssafy.onsikgo.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/register")
    public ResponseEntity<String> register(HttpServletRequest request, @RequestBody StoreDto storeDto) {
        return storeService.register(request, storeDto);
    }

    @PutMapping("/{store_id}")
    public ResponseEntity<String> modify(HttpServletRequest request,
                                         @PathVariable Long store_id,
                                         @RequestBody StoreDto storeDto) {
        return storeService.modify(request, store_id, storeDto);
    }


}
