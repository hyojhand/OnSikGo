package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.ListDto;
import com.ssafy.onsikgo.dto.StoreDto;
import com.ssafy.onsikgo.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @DeleteMapping("/{store_id}")
    public ResponseEntity<String> delete(@PathVariable Long store_id) {
        return storeService.delete(store_id);
    }

    @GetMapping("/{store_id}")
    public ResponseEntity<StoreDto> getInfo(@PathVariable Long store_id) {
        return storeService.getInfo(store_id);
    }

    @PostMapping("/list")
    public ResponseEntity<List<StoreDto>> getList(@RequestBody ListDto listDto) {
        return storeService.getList(listDto);
    }

    @PutMapping("/close/{store_id}")
    public ResponseEntity<String> closeStore(@PathVariable Long store_id) {
        return storeService.closeStore(store_id);
    }



}
