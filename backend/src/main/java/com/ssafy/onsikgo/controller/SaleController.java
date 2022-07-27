package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.SaleItemDto;
import com.ssafy.onsikgo.dto.SaleResultDto;
import com.ssafy.onsikgo.service.SaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping("/{store_id}")
    public ResponseEntity<String> register(@RequestBody SaleItemDto saleItemDto, @PathVariable Long store_id) {
        return saleService.register(saleItemDto, store_id);
    }

    @PostMapping("/history/{store_id}")
    public ResponseEntity<SaleResultDto> getSaleResult(@RequestBody HashMap<String, String> map, @PathVariable Long store_id) {
        return saleService.getSaleResult(map,store_id);
    }

    @GetMapping("/list/{store_id}")
    public ResponseEntity<List<SaleItemDto>> getSaleItemList(@PathVariable Long store_id) {
        return saleService.getSaleItemList(store_id);
    }

    @PatchMapping("/{sale_item_id}")
    public ResponseEntity<String> updateStock(@RequestBody HashMap<String,Integer> map, @PathVariable Long sale_item_id) {
        return saleService.updateStock(map,sale_item_id);
    }

    @DeleteMapping("/{sale_item_id}")
    public ResponseEntity<String> delete(@PathVariable Long sale_item_id) {
        return saleService.delete(sale_item_id);
    }
}