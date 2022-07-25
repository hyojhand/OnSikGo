package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.StoreDto;
import com.ssafy.onsikgo.entity.Store;
import com.ssafy.onsikgo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;


    @Transactional
    public ResponseEntity<StoreDto> register(StoreDto storeDto) {

        String apiKey = "57a2eb95ed5c50c6a133bae6b8980f38";
        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";
        String jsonString = null;
        String roadFullAddr = storeDto.getLocation();

        try {
            roadFullAddr = URLEncoder.encode(roadFullAddr, "UTF-8");

            String addr = apiUrl + "?query=" + roadFullAddr;

            URL url = new URL(addr);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);

            BufferedReader rd = null;
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuffer docJson = new StringBuffer();

            String line;

            while ((line=rd.readLine()) != null) {
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

//        String uri;
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "KakaoAK 57a2eb95ed5c50c6a133bae6b8980f38");
//
//        UriComponentsBuilder builder = null;
//        builder = UriComponentsBuilder.fromHttpUrl(apiUrl+"?query="+roadFullAddr);
////                .queryParam("query", roadFullAddr);
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        ParameterizedTypeReference<Map<String, Object>> typeRef = new ParameterizedTypeReference<Map<String, Object>>() {
//        };
//        uri = builder.encode().toUriString();
//        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(uri, HttpMethod.GET, entity, typeRef);
//
//        JSONParser parser = new JSONParser();
//        try {
//            JSONObject jsonObject = (JSONObject) parser.parse(response.getBody().toString());
//            JSONArray docuArray = (JSONArray) jsonObject.get("documents");
//            JSONObject docuObject = (JSONObject) docuArray.get(0);
//
//            log.info(String.valueOf(docuObject.get("x")));
//            log.info(String.valueOf(docuObject.get("y")));
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        Store store = Store.builder()
                .storeName(storeDto.getStoreName())
                .location(storeDto.getLocation())
                .tel(storeDto.getTel())
                .storeNum(storeDto.getStoreNum())
                .openDate(storeDto.getOpenDate())
                .ownerName(storeDto.getOwnerName())
                .storeImgUrl(storeDto.getStoreImgUrl())
                .openingTime(storeDto.getOpeningTime())
                .closingTime(storeDto.getClosingTime())
                .category(storeDto.getCategory())
//                .lat()
//                .lng()
                .build();

        storeRepository.save(store);
        return new ResponseEntity<>(storeDto, HttpStatus.OK);
    }
}
