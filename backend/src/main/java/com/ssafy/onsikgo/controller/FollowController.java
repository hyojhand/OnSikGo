package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.StoreDto;
import com.ssafy.onsikgo.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @GetMapping("/{store_id}")
    public ResponseEntity<String> register(HttpServletRequest request,@PathVariable Long store_id){
        return followService.register(request,store_id);
    }
    @GetMapping
    public ResponseEntity<Object> getFollowList(HttpServletRequest request){
        return followService.getFollowList(request);
    }
}
