package com.ssafy.onsikgo.dto;

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
}
