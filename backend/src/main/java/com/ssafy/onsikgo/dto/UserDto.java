package com.ssafy.onsikgo.dto;

import com.ssafy.onsikgo.entity.Role;
import lombok.*;

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
    private Role role;
}
