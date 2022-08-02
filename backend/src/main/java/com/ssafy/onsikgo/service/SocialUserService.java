package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.UserDto;

public interface SocialUserService {
    UserDto getUserInfoByAccessToken(String access_token);
    UserDto StringToDto(String userInfo);
}
