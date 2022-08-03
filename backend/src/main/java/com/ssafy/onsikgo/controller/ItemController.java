package com.ssafy.onsikgo.controller;


import com.ssafy.onsikgo.dto.ItemDto;
import com.ssafy.onsikgo.dto.PageDto;
import com.ssafy.onsikgo.dto.SelectDto;
import com.ssafy.onsikgo.entity.Item;
import com.ssafy.onsikgo.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/register/{store_id}")
    public ResponseEntity<String> register(@RequestPart(value = "file",required = false) MultipartFile file,
                                           @RequestPart(value = "itemDto",required = false) ItemDto itemDto, @PathVariable Long store_id) {
        return itemService.register(file, itemDto,store_id);
    }

    @GetMapping("/{item_id}")
    public ResponseEntity<ItemDto> getInfo(@PathVariable Long item_id) {
        return itemService.getInfo(item_id);
    }

    @DeleteMapping("/{item_id}")
    public ResponseEntity<String> delete(@PathVariable Long item_id) {
        return itemService.delete(item_id);
    }

    @PutMapping("/{item_id}")
    public ResponseEntity<String> modify(@RequestPart(value = "file",required = false) MultipartFile file,
                                         @RequestPart(value = "itemDto",required = false) ItemDto itemDto, @PathVariable Long item_id) {
        return itemService.modify(file,itemDto, item_id);
    }

    @GetMapping("/list/{store_id}")
    public ResponseEntity<List<ItemDto>> getList(@PathVariable Long store_id) {
        return itemService.getList(store_id);
    }

    @PostMapping("/list/keyword/{store_id}")
    public ResponseEntity<List<ItemDto>> getList(@PathVariable Long store_id, @RequestBody SelectDto selectDto) {
        return itemService.getKeyword(store_id, selectDto);
    }

    @PostMapping("/page/{store_id}")
    public ResponseEntity<Page<ItemDto>> getListPage(@RequestBody PageDto pageDto, @PathVariable Long store_id) {
        return itemService.getListPage(pageDto,store_id);
    }



}
