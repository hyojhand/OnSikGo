package com.ssafy.onsikgo.entity;

import com.ssafy.onsikgo.dto.UserDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String imgUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoginType loginType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // 사용자 역할 [USER, OWNER]

    @OneToMany(mappedBy = "user")
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Store> stores = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Follow> follows = new ArrayList<>();

    public User update(String nickname, String imgUrl) {
        this.nickname = nickname;
        this.imgUrl = imgUrl;
        return this;
    }

    public User changePw(String password) {
        this.password = password;
        return this;
    }

    public UserDto toDto() {
        return UserDto.builder()
                .email(this.email)
                .nickname(this.nickname)
                .userName(this.userName)
                .imgUrl(this.imgUrl)
                .role(this.role)
                .loginType(this.loginType.toString())
                .build();
    }

}
