package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor //final 필드에 대한 생성자 생성
@RestController
@RequestMapping("/api")
public class AwsS3Controller {
    private final AwsS3Service awsS3Service;

    //test용 나중에 삭제 예정
    @PostMapping("/hello")
    public String uploadImage(@RequestPart(value = "file",required = false) MultipartFile file){
        return awsS3Service.uploadImge(file);
    }
}
