package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.ReviewDto;
import com.ssafy.onsikgo.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    @DeleteMapping("/{email}")
    public ResponseEntity<String> delete(@PathVariable String email, HttpServletRequest request){
        return adminService.deleteUser(request,email);
    }
    @GetMapping("/reportedReviews")
    public ResponseEntity<List<ReviewDto>> getReportedReviews(HttpServletRequest request){
        return adminService.getReportedReviews(request);
    }
}
