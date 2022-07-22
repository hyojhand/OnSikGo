package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.LoginDto;
import com.ssafy.onsikgo.dto.TokenDto;
import com.ssafy.onsikgo.dto.UserDto;
import com.ssafy.onsikgo.entity.Authority;
import com.ssafy.onsikgo.entity.LoginType;
import com.ssafy.onsikgo.entity.Role;
import com.ssafy.onsikgo.entity.User;
import com.ssafy.onsikgo.repository.UserRepository;
import com.ssafy.onsikgo.security.JwtFilter;
import com.ssafy.onsikgo.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public ResponseEntity<String> checkEmail(String email) {
        if(userRepository.findByEmail(email).orElse(null) != null) {
            log.info("이미존재하는 이메일");
            return new ResponseEntity<>("이미 존재하는 이메일입니다.", HttpStatus.NO_CONTENT);
        }
        log.info("사용가능한 이메일");
        return new ResponseEntity<>("사용가능한 이메일입니다.", HttpStatus.OK);
    }

    public ResponseEntity<String> checkNickname(String nickname) {
        if(userRepository.findByNickname(nickname).orElse(null) != null) {
            log.info("이미존재하는 닉네임");
            return new ResponseEntity<>("이미 존재하는 닉네임입니다.", HttpStatus.NO_CONTENT);
        }
        log.info("사용가능한 닉네임");
        return new ResponseEntity<>("사용가능한 닉네임입니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<UserDto> signup(UserDto userDto) {

        // 권한정보 생성 (ROLE_USER 권한으로 저장)
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .userName(userDto.getUserName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .imgUrl(userDto.getImgUrl())
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .role(Role.USER)
                .loginType(LoginType.ONSIKGO)
                .build();

        userRepository.save(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
//        return userDto;
    }

    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {

        //  LoginDto의 userName,Password를 받아서 UsernamePasswordAuthenticationToken 객체를 생성한다
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        // authenticationToken 을 이용해서 Authentication 객체를 생성하려고 authenticate메서드가 실행될때
        // CustomUserDetailsService 의 loadUserByUsername 메서드가 실행된다.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 생성된 Authentication 객체를 SecurityContextHolder에 저장하고,
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 그 인증정보를 기반으로 토큰을 생성한다
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        // 생성한 토큰을 Response 헤더에 넣어주고,
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        // TokenDto에도 넣어서 RequestBody로 리턴해준다

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}
