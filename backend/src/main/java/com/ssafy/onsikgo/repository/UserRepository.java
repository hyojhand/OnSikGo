package com.ssafy.onsikgo.repository;

import com.ssafy.onsikgo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

//    User findByEmail(String email);
//    User findByNickname(String nickname);

    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
}
