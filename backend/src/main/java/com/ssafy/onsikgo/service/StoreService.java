package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.*;
import com.ssafy.onsikgo.entity.*;
import com.ssafy.onsikgo.repository.SaleRepository;
import com.ssafy.onsikgo.repository.StoreRepository;
import com.ssafy.onsikgo.repository.UserRepository;
import com.ssafy.onsikgo.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final SaleRepository saleRepository;
    private final AwsS3Service awsS3Service;
    private final SaleService saleService;
    private final String defaultImg = "https://onsikgo.s3.ap-northeast-2.amazonaws.com/store/noimage.png";
    @Value("${java.file.kakao-api}")
    private String kakao;

    @Transactional
    public ResponseEntity<String> firstRegister(OwnerDto ownerDto, User user) {

        HashMap<String, String> coordinate = getCoordinate(ownerDto.getAddress());
        Store store = ownerDto.toStoreEntity(coordinate);
        store.addUser(user);

        storeRepository.save(store);
        return new ResponseEntity<>("가게 정보가 등록되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> register(HttpServletRequest request, MultipartFile file, StoreDto storeDto) {

        String token = request.getHeader("access-token");
        User findUser = null;
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        findUser = userRepository.findByEmail(userEmail).get();


        String storeImgUrl = defaultImg;

        if(!file.isEmpty()){
            storeImgUrl = awsS3Service.uploadImge(file);
        }
        storeDto.setStoreImgUrl(storeImgUrl);

        HashMap<String, String> coordinate = getCoordinate(storeDto.getAddress());
        Store store = storeDto.toEntity(coordinate);
        store.addUser(findUser);

        storeRepository.save(store);
        return new ResponseEntity<>("가게 정보가 등록되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> modify(HttpServletRequest request, Long store_id, MultipartFile file, StoreDto storeDto) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }

        Store findStore = storeRepository.findById(store_id).get();

        HashMap<String, String> coordinate = getCoordinate(storeDto.getAddress());

        if(file == null) {
            storeDto.setStoreImgUrl(findStore.getStoreImgUrl());
        } else {
            if (!findStore.getStoreImgUrl().equals(defaultImg)) {
                awsS3Service.delete(findStore.getStoreImgUrl());
            }
            String storeImgUrl = awsS3Service.uploadImge(file);
            storeDto.setStoreImgUrl(storeImgUrl);
        }
        findStore.update(storeDto, coordinate);

        storeRepository.save(findStore);
        return new ResponseEntity<>("가게 정보가 수정되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> delete(Long store_id) {

        Optional<Store> findStore = storeRepository.findById(store_id);
        storeRepository.delete(findStore.get());
        return new ResponseEntity<>("가게 정보가 삭제되었습니다.", HttpStatus.OK);
    }

    public ResponseEntity<StoreDto> getInfo(Long store_id) {
        Store findStore = storeRepository.findById(store_id).get();

        StoreDto findStoreDto = findStore.toDto();
        return new ResponseEntity<>(findStoreDto, HttpStatus.OK);
    }

    public ResponseEntity<List<StoreDto>> getList(HttpServletRequest request) {
        String token = request.getHeader("access-token");
        User findUser = null;
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        findUser = userRepository.findByEmail(userEmail).get();

        List<Store> storeList = storeRepository.findByUser(findUser);

        return new ResponseEntity<>(getSortList(storeList), HttpStatus.OK);
    }
    public List<StoreDto> getSortList(List<Store> storeList){
        List<StoreDto> list = new ArrayList<>();
        for(Store store : storeList) {
            List<SaleItemDto> saleItemDtos = saleService.getSaleItemList(store.getStoreId());
            StoreDto storeDto = store.toDto();
            storeDto.setTotalStock(0);
            if(saleItemDtos!=null){
                storeDto.setTotalStock(saleItemDtos.size());
            }
            list.add(storeDto);
        }
        Collections.sort(list);
        return list;
    }
    public ResponseEntity<List<StoreDto>> getCategoryKeyword(SelectDto selectDto) {
        if(selectDto.getKeyword() != null && selectDto.getCategory() != null) {
            List<Store> storeList = storeRepository.findByStoreNameContainingAndCategory(selectDto.getKeyword(),Category.valueOf(selectDto.getCategory()));
            List<StoreDto> storeDtoList = new ArrayList<>();
            for(int i = 0; i < storeList.size(); i++) {
                storeDtoList.add(storeList.get(i).toDto());
            }
            return new ResponseEntity<>(getSortList(storeList), HttpStatus.OK);
        }

        if(selectDto.getKeyword() != null) {
            List<Store> storeList = storeRepository.findByStoreNameContaining(selectDto.getKeyword());
            List<StoreDto> storeDtoList = new ArrayList<>();
            for(int i = 0; i < storeList.size(); i++) {
                storeDtoList.add(storeList.get(i).toDto());
            }
            return new ResponseEntity<>(getSortList(storeList), HttpStatus.OK);
        }

        if(selectDto.getCategory() != null) {
            List<Store> storeList = storeRepository.findByCategory(Category.valueOf(selectDto.getCategory()));
            List<StoreDto> storeDtoList = new ArrayList<>();
            for(int i = 0; i < storeList.size(); i++) {
                storeDtoList.add(storeList.get(i).toDto());
            }
            return new ResponseEntity<>(getSortList(storeList), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


    @Transactional
    public ResponseEntity<String> closeStore(Long store_id) {

        Optional<Store> findStore = storeRepository.findById(store_id);
        if(!findStore.isPresent()) {
            return new ResponseEntity<>("해당하는 가게가 없습니다.", HttpStatus.NOT_FOUND);
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
        String time = now.format(timeFormatter);
        if(Integer.parseInt(time) < 6) {
            now = now.minusDays(1);
        }

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = now.format(dayFormatter);

        Optional<Sale> findSale = saleRepository.findByStoreAndDateAndClosedFalse(findStore.get(), date);
        if(!findSale.isPresent()) {
            return new ResponseEntity<>("fail", HttpStatus.NO_CONTENT);
        }

        findSale.get().updateClosed();
        saleRepository.save(findSale.get());



        return new ResponseEntity<>("가게 결산이 완료되었습니다.", HttpStatus.OK);
    }

    public ResponseEntity<SaleDto> getSaleInfo(Long store_id) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
        String time = now.format(timeFormatter);
        if(Integer.parseInt(time) < 6) {
            now = now.minusDays(1);
        }

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = now.format(dayFormatter);

        Optional<Store> findStore = storeRepository.findById(store_id);
        Optional<Sale> findSale = saleRepository.findByStoreAndDate(findStore.get(), date);

        if(!findSale.isPresent()) {
            SaleDto saleDto = new SaleDto();
            saleDto.setClosed(false);
            saleDto.setStoreDto(findStore.get().toDto());
            return new ResponseEntity<>(saleDto, HttpStatus.OK);
        }

        SaleDto saleDto = findSale.get().toDto(findStore.get().toDto());

        return new ResponseEntity<>(saleDto, HttpStatus.OK);
    }

    public ResponseEntity<List<StoreDto>> getKeyword(SelectDto selectDto) {
        List<Store> storeList = storeRepository.findByStoreNameContaining(selectDto.getKeyword());
        List<StoreDto> storeDtoList = new ArrayList<>();
        for(int i = 0; i < storeList.size(); i++) {
            storeDtoList.add(storeList.get(i).toDto());
        }
        return new ResponseEntity<>(getSortList(storeList), HttpStatus.OK);
    }

    // 좌표 가져오는 메서드
    public HashMap<String, String> getCoordinate(String fullAddress) {

        String apiKey = kakao;
        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";
        String jsonString = null;

        try {
            fullAddress = URLEncoder.encode(fullAddress, "UTF-8");

            String addr = apiUrl + "?query=" + fullAddress;

            URL url = new URL(addr);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);

            BufferedReader rd = null;
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuffer docJson = new StringBuffer();

            String line;

            while ((line = rd.readLine()) != null) {
                docJson.append(line);
            }

            jsonString = docJson.toString();
            rd.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info(jsonString);

        String lat = "";
        String lng = "";
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONArray jsonArray = (JSONArray) jsonObject.get("documents");
            JSONObject object = (JSONObject) jsonArray.get(0);
            lat = object.get("y").toString();
            lng = object.get("x").toString();
            log.info(lat);
            log.info(lng);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("lat", lat);
        map.put("lng", lng);

        return map;
    }

    public ResponseEntity<List<StoreDto>> getTotal() {
        List<Store> storeList = storeRepository.findAll();
        List<StoreDto> storeDtoList = new ArrayList<>();
        for(Store store : storeList) {
            storeDtoList.add(store.toDto());
        }
        return new ResponseEntity<>(getSortList(storeList), HttpStatus.OK);
    }
}
