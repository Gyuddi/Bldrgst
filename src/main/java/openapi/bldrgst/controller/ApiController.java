package openapi.bldrgst.controller;

import openapi.bldrgst.service.ApiService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
public class ApiController {

    @Autowired
    ApiService apiService;

    @GetMapping("/api")
    public String callApi() throws IOException, ParseException {
        String returnAll = "";
        for (int i = 0; i < 43; i++) {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/BldRgstService_v2/getBrTitleInfo"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=tRBZ54O7jUvEOuRoXbTpPm%2B5%2FmsduwNiuZebXBmVeXFcXwzTbFve10xsVNH%2FnVhw5mF%2FL9dnxzMwxGK27ACXJg%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("sigunguCd", "UTF-8") + "=" + URLEncoder.encode("50110", "UTF-8")); /*행정표준코드*/
            urlBuilder.append("&" + URLEncoder.encode("bjdongCd", "UTF-8") + "=" + URLEncoder.encode("12200", "UTF-8")); /*행정표준코드*/
//        urlBuilder.append("&" + URLEncoder.encode("platGbCd","UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")); /*0:대지 1:산 2:블록*/
//        urlBuilder.append("&" + URLEncoder.encode("bun","UTF-8") + "=" + URLEncoder.encode("3810", "UTF-8")); /*번*/
//        urlBuilder.append("&" + URLEncoder.encode("ji","UTF-8") + "=" + URLEncoder.encode("0012", "UTF-8")); /*지*/
//        urlBuilder.append("&" + URLEncoder.encode("startDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*YYYYMMDD*/
//        urlBuilder.append("&" + URLEncoder.encode("endDate","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*YYYYMMDD*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10000000", "UTF-8")); /*페이지당 목록 수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(i), "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line + "\n\r");
            }
            rd.close();
            conn.disconnect();


            apiService.init(sb.toString());
            returnAll += sb.toString();
        }
        return returnAll;
    }
}
