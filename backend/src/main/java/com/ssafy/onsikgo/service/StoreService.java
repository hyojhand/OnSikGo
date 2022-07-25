package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.StoreDto;
import com.ssafy.onsikgo.entity.Store;
import com.ssafy.onsikgo.entity.User;
import com.ssafy.onsikgo.repository.StoreRepository;
import com.ssafy.onsikgo.repository.UserRepository;
import com.ssafy.onsikgo.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;


    @Transactional
    public ResponseEntity<String> register(HttpServletRequest request, StoreDto storeDto) {

        String token = request.getHeader("access-token");
        User findUser = null;
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));

        findUser = userRepository.findByEmail(userEmail).get();


        HashMap<String, String> coordinate = getCoordinate(storeDto.getLocation());
        Store store = storeDto.toEntity(coordinate);
        store.addUser(findUser);

        storeRepository.save(store);
        return new ResponseEntity<>("가게 정보가 등록되었습니다.", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> modify(HttpServletRequest request, Long store_id, StoreDto storeDto) {
        String token = request.getHeader("access-token");
        if (!tokenProvider.validateToken(token)) {
            return new ResponseEntity<>("유효하지 않는 토큰", HttpStatus.NO_CONTENT);
        }

        String userEmail = String.valueOf(tokenProvider.getPayload(token).get("sub"));
        User findUser = userRepository.findByEmail(userEmail).get();

        Store findStore = storeRepository.findById(store_id).get();

        HashMap<String, String> coordinate = getCoordinate(storeDto.getLocation());
        findStore = storeDto.toEntity(coordinate);
        findStore.addUser(findUser);

        storeRepository.save(findStore);
        return new ResponseEntity<>("가게 정보가 수정되었습니다.", HttpStatus.OK);
    }

    // 좌표 가져오는 메서드
    public HashMap<String, String> getCoordinate(String fullAddress) {

        String apiKey = "57a2eb95ed5c50c6a133bae6b8980f38";
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
}
