package com.ssafy.onsikgo.service;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;

@Service
@Slf4j
public class FcmService {

    @Value("${java.file.firebase-server-key}")
    private String fcm;

    public void send(String to) {
        String apiUrl = "https://fcm.googleapis.com/fcm/send";
        String jsonString = null;

        JSONObject noti = new JSONObject();
        noti.put("title", "온식고의 알림이 도착했습니다.");
        noti.put("body", "온식고를 확인해주세요!");
        noti.put("click_action", "https://i7e201.p.ssafy.io/");

        JSONObject reqParams = new JSONObject();
        reqParams.put("notification", noti); // body에 들어갈 내용을 담는다.
        reqParams.put("to",to);

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", fcm);
            conn.setDoOutput(true);

            OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
            os.write(reqParams.toString());
            os.flush();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            JSONObject jsonObj = (JSONObject) JSONValue.parse(rd.readLine());
            rd.close();
            conn.disconnect();
            log.info((String) jsonObj.get("result"));

//            StringBuffer docJson = new StringBuffer();
//
//            String line;
//
//            while ((line = rd.readLine()) != null) {
//                docJson.append(line);
//            }
//
//            jsonString = docJson.toString();
//            rd.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
