package com.ssafy.onsikgo.dto;

import com.ssafy.onsikgo.entity.Authority;
import com.ssafy.onsikgo.entity.LoginType;
import com.ssafy.onsikgo.entity.Role;
import com.ssafy.onsikgo.entity.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String email;
    private String password;
    private String userName;
    private String nickname;
    private String imgUrl;
    private MultipartFile file;
    private Role role;

    public User toEntity(LoginType loginType){

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        return User.builder()
                .userName(this.getUserName())
                .password(this.getPassword())
                .email(this.getEmail())
                .imgUrl(this.getImgUrl())
                .nickname(this.getNickname())
                .authorities(Collections.singleton(authority))
                .role(this.role)
                .loginType(loginType)
                .build();
    }
}
