package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.LoginDto;
import com.ssafy.onsikgo.dto.UserDto;
import com.ssafy.onsikgo.entity.LoginType;
import com.ssafy.onsikgo.entity.Role;
import com.ssafy.onsikgo.entity.User;
import com.ssafy.onsikgo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoUserService implements SocialUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final String defaultImg = "https://onsikgo.s3.ap-northeast-2.amazonaws.com/user/pngwing.com.png";

    @Override
    @Transactional
    public UserDto getUserInfoByAccessToken(String access_token) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        String result = "";
        try {

            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            // 전송할 header 작성
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + access_token);
            conn.setRequestProperty("charset", "UTF-8");

            // 결과 확인
            int responseCode = conn.getResponseCode();
            log.debug("responseCode : " + responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
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

        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        // 일치하는 회원 X -> 가입
        if (!user.isPresent()) {
            userService.signup(userDto, LoginType.KAKAO);
        }
        return userDto;
    }
    @Override
    public HttpEntity<? extends Object> login(UserDto userDto,String fcm_token) {
        User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        // 이미 가입된 이메일
        if (!user.getLoginType().toString().equals(LoginType.KAKAO.toString())) {
            return new ResponseEntity<>("이미 가입된 메일입니다.", HttpStatus.NO_CONTENT);
        }
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(userDto.getEmail());
        loginDto.setPassword(userDto.getPassword());
        loginDto.setTo(fcm_token);
        return userService.login(loginDto);
    }

    @Override
    public UserDto StringToDto(String userInfo) {
        UserDto userDto = new UserDto();
        try {
            // JSON 파싱
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(userInfo);

            JSONObject kakao_account = (JSONObject) jsonObj.get("kakao_account");
            JSONObject profile = (JSONObject) kakao_account.get("profile");
            userDto.setRole(Role.USER);

            String email = jsonObj.get("id").toString()+"KAKAO@onsikgo.com";
            userDto.setEmail(email);
            userDto.setPassword(jsonObj.get("id").toString());

            Optional user = userRepository.findByEmail(email);
            if(user.isPresent()){
                return userDto;
            }
            String nickname;
            String temp_nickname = UUID.randomUUID().toString().replaceAll("-", "");
            temp_nickname = "User"+temp_nickname.substring(0, 10);
            nickname=(String)profile.getOrDefault("nickname",temp_nickname);
            userDto.setNickname(nickname);

            userDto.setUserName(nickname);
            String profile_image_url= (String)profile.getOrDefault("profile_image_url",defaultImg);
            userDto.setImgUrl(profile_image_url);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return userDto;
    }
}
