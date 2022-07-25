package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.StoreDto;
import com.ssafy.onsikgo.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/register")
    public ResponseEntity<StoreDto> register(@RequestBody StoreDto storeDto) {
        return storeService.register(storeDto);
    }


}
