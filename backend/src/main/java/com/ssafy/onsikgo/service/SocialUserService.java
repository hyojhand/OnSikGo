package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface SocialUserService {
    ResponseEntity<String> getUserInfoByAccessToken(String access_token);
    UserDto StringToDto(String userInfo);
}
