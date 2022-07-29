package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.LoginDto;
import com.ssafy.onsikgo.dto.TokenDto;
import com.ssafy.onsikgo.dto.UserDto;
import com.ssafy.onsikgo.service.KakaoUserService;
import com.ssafy.onsikgo.service.NaverUserService;
import com.ssafy.onsikgo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;
    private final NaverUserService naverUserService;
    @PostMapping("/email")
    public ResponseEntity<String> checkEmail(@RequestBody HashMap<String, Object> map) {
        String email = (String)map.get("email");
        return userService.checkEmail(email);
    }
    @PostMapping("/emailAuthNumber")
    public ResponseEntity<String> checkAuthNumber(@RequestBody HashMap<String, Object> map) {
        String authNum = (String) map.get("authNum");
        String email = (String)map.get("email");
        return userService.checkAuthNumber(email,authNum);
    }
    @PostMapping("/nickname")
    public ResponseEntity<String> checkNickname(@RequestBody UserDto userDto) {
        return userService.checkNickname(userDto.getNickname());
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto userDto) {
        return userService.signup(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @PatchMapping
    public ResponseEntity<String> modify(
            @Valid @RequestPart UserDto userDto,
            @RequestPart(required = false) MultipartFile file,
            HttpServletRequest request) {
        userDto.setFile(file);
        return userService.modify(userDto, request);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(HttpServletRequest request) {
        return userService.delete(request);
    }

    @PostMapping("/pw-check")
    public ResponseEntity<String> pwCheck(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        return userService.pwCheck(loginDto,request);
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
    public ResponseEntity<String> kakaoLogin(@RequestBody HashMap<String, String> param){
        return kakaoUserService.getUserInfoByAccessToken(param.get("access_token"));
    }
    @PostMapping("/naver")
    public ResponseEntity<String> naverLogin(@RequestBody HashMap<String, String> param){
        return naverUserService.getUserInfoByAccessToken(param.get("access_token"));
    }
}
