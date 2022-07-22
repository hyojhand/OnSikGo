package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.LoginDto;
import com.ssafy.onsikgo.dto.TokenDto;
import com.ssafy.onsikgo.dto.UserDto;
import com.ssafy.onsikgo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/email")
    public ResponseEntity<String> checkEmail(@RequestBody UserDto userDto) {
        return userService.checkEmail(userDto.getEmail());
    }

    @PostMapping("/nickname")
    public ResponseEntity<String> checkNickname(@RequestBody UserDto userDto) {
        return userService.checkNickname(userDto.getNickname());
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto) {
        return userService.signup(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
//        return ResponseEntity.of(userService.login(loginDto));
    }



}
