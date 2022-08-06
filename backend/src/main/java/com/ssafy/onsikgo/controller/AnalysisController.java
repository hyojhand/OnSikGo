package com.ssafy.onsikgo.controller;

import com.ssafy.onsikgo.dto.HistoryReqDto;
import com.ssafy.onsikgo.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@Slf4j
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;

    @PostMapping("/sale-history")
    public ResponseEntity<Object> getSaleHistory(@RequestBody HistoryReqDto historyReqDto, HttpServletRequest request){
        log.info(historyReqDto.toString());
        return analysisService.getSaleHistory(historyReqDto,request);
    }
}
