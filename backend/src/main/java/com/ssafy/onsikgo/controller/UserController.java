package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.LoginDto;
import com.ssafy.onsikgo.dto.OwnerDto;
import com.ssafy.onsikgo.dto.TokenDto;
import com.ssafy.onsikgo.dto.UserDto;
import com.ssafy.onsikgo.entity.LoginType;
import com.ssafy.onsikgo.service.KakaoUserService;
import com.ssafy.onsikgo.service.NaverUserService;
import com.ssafy.onsikgo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;
    private final NaverUserService naverUserService;

    @PostMapping("/email")
    public ResponseEntity<String> checkEmail(@RequestBody HashMap<String, Object> map) {
        String email = (String) map.get("email");
        return userService.checkEmail(email);
    }

    @PostMapping("/emailAuthNumber")
    public ResponseEntity<String> checkAuthNumber(@RequestBody HashMap<String, Object> map) {
        String authNum = (String) map.get("authNum");
        String email = (String) map.get("email");
        return userService.checkAuthNumber(email, authNum);
    }

    @PostMapping("/nickname")
    public ResponseEntity<String> checkNickname(@RequestBody UserDto userDto) {
        return userService.checkNickname(userDto.getNickname());
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto userDto) {
        return userService.signup(userDto, LoginType.ONSIKGO);
    }

    @PostMapping("/signup/owner")
    public ResponseEntity<String> signup(@RequestBody OwnerDto ownerDto) {
        return userService.signupOwner(ownerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @PutMapping
    public ResponseEntity<String> modify(
            @RequestPart(value = "file",required = false) MultipartFile file,
            @RequestPart(value = "userDto",required = false) UserDto userDto,
            HttpServletRequest request) {
        return userService.modify(userDto,file, request);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(HttpServletRequest request) {
        return userService.delete(request);
    }

    @PostMapping("/pw-check")
    public ResponseEntity<String> pwCheck(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        return userService.pwCheck(loginDto, request);
    }

    @PatchMapping("/pw")
    public ResponseEntity<String> pwChange(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        return userService.pwChange(loginDto, request);
    }

    @GetMapping
    public ResponseEntity<UserDto> getInfo(HttpServletRequest request) {
        return userService.getInfo(request);
    }

    @PostMapping("/kakao")
    public HttpEntity<?> kakaoLogin(@RequestBody HashMap<String, String> param) {
        kakaoUserService.getUserInfoByAccessToken(param.get("access_token"));
        UserDto userDto = kakaoUserService.getUserInfoByAccessToken(param.get("access_token"));
        return kakaoUserService.login(userDto);
    }

    @PostMapping("/naver")
    public HttpEntity<?> naverLogin(@RequestBody HashMap<String, String> param) {
        naverUserService.getUserInfoByAccessToken(param.get("access_token"));
        UserDto userDto = naverUserService.getUserInfoByAccessToken(param.get("access_token"));
        return naverUserService.login(userDto);
    }
    @PostMapping("/pw-find")
    public ResponseEntity<String> findPw(@RequestBody HashMap<String, String> map) {
        return userService.findPw(map);
    }
}
