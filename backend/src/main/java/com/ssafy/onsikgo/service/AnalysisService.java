package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.*;
import com.ssafy.onsikgo.entity.Sale;
import com.ssafy.onsikgo.entity.SaleItem;
import com.ssafy.onsikgo.entity.Store;
import com.ssafy.onsikgo.repository.SaleRepository;
import com.ssafy.onsikgo.repository.StoreRepository;
import com.ssafy.onsikgo.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnalysisService {

    private final StoreRepository storeRepository;
    private final TokenProvider tokenProvider;
    private final SaleRepository saleRepository;

    public ResponseEntity<Object> getSaleHistory(HistoryReqDto historyReqDto, HttpServletRequest request){
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }
        Store store = storeRepository.findById(historyReqDto.getStoreId()).get();

        List<Sale> list= saleRepository.findByStoreAndDateBetween(store,
                historyReqDto.getStartDate().replaceAll("-",""),
                historyReqDto.getEndDate().replaceAll("-",""));
        Map<String, Object> result = new HashMap<>();

        int total_revenue=0;
        for(Sale sale:list){
            for(SaleItem saleItem:sale.getSaleItems()){
                    HistoryDto historyResDto = (HistoryDto)result.getOrDefault(saleItem.getItem().getItemName(),new HistoryDto());
                    int total = saleItem.getTotalStock();
                    int left = saleItem.getStock();
                    int sold = total-left;
                    historyResDto.update(total,sold,left);
                    result.put(saleItem.getItem().getItemName(),historyResDto);
                }
            total_revenue+=sale.getTotalPrice();
            }

        HistoryResDto historyResDto = new HistoryResDto(total_revenue);

        for( String strKey : result.keySet() ){
            HistoryDto historyDto = (HistoryDto) result.get(strKey);

            SoldRatioDto soldRatioDto = new SoldRatioDto();
            LeftRatioDto leftRatioDto = new LeftRatioDto();

            soldRatioDto.setName(strKey);
            leftRatioDto.setName(strKey);
            double value = (Math.round((((double)historyDto.getSold()/(double)historyDto.getTotal()))*100)*100)/100.0;
            soldRatioDto.setValue(value);
            leftRatioDto.setValue(100-value);

            soldRatioDto.setSold(historyDto.getSold());
            leftRatioDto.setLeft(historyDto.getLeft());

            historyResDto.getGood().add(soldRatioDto);
            historyResDto.getBad().add(leftRatioDto);
        }

        return new ResponseEntity<>(historyResDto, HttpStatus.OK);
        }
    }
