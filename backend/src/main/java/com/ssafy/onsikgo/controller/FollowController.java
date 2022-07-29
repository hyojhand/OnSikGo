package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.StoreDto;
import com.ssafy.onsikgo.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @GetMapping("/{store_id}")
    public ResponseEntity<String> register(HttpServletRequest request,@PathVariable String store_id){
        return followService.register(request,store_id);
    }
    @GetMapping
    public ResponseEntity<Object> getFollowList(HttpServletRequest request){
        return followService.getFollowList(request);
    }
    @DeleteMapping("/{store_id}")
    public ResponseEntity<Object> delete(HttpServletRequest request,@PathVariable String store_id){
        return followService.delete(request,store_id);
    }
}
