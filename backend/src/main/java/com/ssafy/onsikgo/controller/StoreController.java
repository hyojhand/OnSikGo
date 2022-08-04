package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.ItemDto;
import com.ssafy.onsikgo.dto.SaleDto;
import com.ssafy.onsikgo.dto.SelectDto;
import com.ssafy.onsikgo.dto.StoreDto;
import com.ssafy.onsikgo.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/register")
    public ResponseEntity<String> register(HttpServletRequest request,
                                           @RequestPart(value = "file",required = false) MultipartFile file,
                                           @RequestPart(value = "storeDto",required = false) StoreDto storeDto) {
        return storeService.register(request,file, storeDto);
    }

    @PutMapping("/{store_id}")
    public ResponseEntity<String> modify(HttpServletRequest request,
                                         @PathVariable Long store_id,
                                         @RequestPart(value = "file",required = false) MultipartFile file,
                                         @RequestPart(value = "storeDto",required = false) StoreDto storeDto) {
        return storeService.modify(request, store_id, file, storeDto);
    }

    @DeleteMapping("/{store_id}")
    public ResponseEntity<String> delete(@PathVariable Long store_id) {
        return storeService.delete(store_id);
    }

    @GetMapping("/{store_id}")
    public ResponseEntity<StoreDto> getInfo(@PathVariable Long store_id) {
        return storeService.getInfo(store_id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<StoreDto>> getList(HttpServletRequest request) {
        return storeService.getList(request);
    }

    @PostMapping("/list")
    public ResponseEntity<List<StoreDto>> getCategoryKeyword(@RequestBody SelectDto selectDto) {
        return storeService.getCategoryKeyword(selectDto);
    }

    @PutMapping("/close/{store_id}")
    public ResponseEntity<String> closeStore(@PathVariable Long store_id) {
        return storeService.closeStore(store_id);
    }

    @GetMapping("/close/{store_id}")
    public ResponseEntity<SaleDto> getSaleInfo(@PathVariable Long store_id) {
        return storeService.getSaleInfo(store_id);
    }


}
