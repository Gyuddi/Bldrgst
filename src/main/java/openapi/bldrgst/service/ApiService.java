package openapi.bldrgst.service;

import openapi.bldrgst.entity.Domain;
import openapi.bldrgst.repository.ApiRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class ApiService {

    @Autowired
    ApiRepository apiRepository;

    public void init(String jsonData)  {
        ArrayList<Domain> domains = new ArrayList<>();
        try {
            JSONObject jObj;
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonData);
            JSONObject parseResponse = (JSONObject) jsonObj.get("response");
            JSONObject parseBody = (JSONObject) parseResponse.get("body");

            System.out.println(parseBody.toString());
            JSONObject parseBody2 = (JSONObject) parseBody.get("items");

            JSONArray parseBody3 = (JSONArray) parseBody2.get("item");

            for (Object o : parseBody3) {
                jObj = (JSONObject) o;

                Domain domain = Domain.builder()
                        .mainPurpsCdNm(jObj.get("mainPurpsCdNm").toString())
                        .houseName(jObj.get("bldNm").toString())
                        .buildUse(jObj.get("mainPurpsCdNm").toString())
                        .buildingStructure(jObj.get("strctCdNm").toString())
                        .useAprDay(jObj.get("useAprDay").toString())
                        .grndFloor(jObj.get("grndFlrCnt").toString())
                        .ugrndFloor(jObj.get("ugrndFlrCnt").toString())
                        .elevator(jObj.get("rideUseElvtCnt").toString())
                        .houseHold(jObj.get("hhldCnt").toString())
                        .platPlc(jObj.get("platPlc").toString())
                        .build();
                domains.add(domain);
//                apiRepository.save(domain);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Domain d:domains) {
            if (d.getMainPurpsCdNm().equals("다가구주택") || d.getMainPurpsCdNm().equals("다중주택") || d.getMainPurpsCdNm().equals("공동주택") || d.getMainPurpsCdNm().equals("다세대주택") || d.getMainPurpsCdNm().equals("오피스텔") || d.getMainPurpsCdNm().equals("단독주택")){
                apiRepository.save(d);
            }
        }
    }
}