package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.UserDto;
import org.springframework.http.HttpEntity;

public interface SocialUserService {
    UserDto getUserInfoByAccessToken(String access_token);
    UserDto StringToDto(String userInfo);
    HttpEntity<? extends Object> login(UserDto userDto);
}
