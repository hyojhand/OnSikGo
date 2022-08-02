package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.UserDto;

import com.ssafy.onsikgo.entity.LoginType;
import com.ssafy.onsikgo.entity.User;
import com.ssafy.onsikgo.repository.UserRepository;
import com.ssafy.onsikgo.security.JwtFilter;
import com.ssafy.onsikgo.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class NaverUserService implements SocialUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    @Transactional
    public ResponseEntity<String> getUserInfoByAccessToken(String access_token) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        String result = "";
        try {

            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //전송할 header 작성
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + access_token);
            conn.setRequestProperty("charset", "UTF-8");

            //결과 확인
            int responseCode = conn.getResponseCode();
            log.debug("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.debug("response body : " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserDto userDto = StringToDto(result);

        User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        // 일치하는 회원 X -> 가입
        if (user == null) {
            userRepository.save(userDto.toEntity(LoginType.NAVER));
            log.info("회원가입 완료");
        }
        // 이미 가입된 이메일
        if(!user.getLoginType().equals(LoginType.NAVER)){
            return new ResponseEntity<>("이미 존재하는 이메일", HttpStatus.NO_CONTENT);
        }
        else{
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return new ResponseEntity<>(jwt, HttpStatus.OK);

            // TokenDto에도 넣어서 RequestBody로 리턴해준다
//        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
        }
    }

    @Override
    public UserDto StringToDto(String userInfo) {
        UserDto userDto = new UserDto();
        try {
            //JSON 파싱
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(userInfo);
            JSONObject account = (JSONObject) jsonObj.get("response");

            userDto.setEmail(account.get("email").toString());
            userDto.setNickname(account.get("nickname").toString());
            userDto.setImgUrl(account.get("profile_image").toString());
            userDto.setPassword(passwordEncoder.encode(account.get("id").toString()));

            log.debug(userDto.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return userDto;
    }
}