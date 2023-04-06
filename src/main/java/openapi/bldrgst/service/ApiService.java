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

@Service
@Transactional
public class ApiService {

    @Autowired
    ApiRepository apiRepository;

    public void init(String jsonData) throws ParseException {
        JSONObject jObj;
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonData);
        JSONObject parseResponse = (JSONObject) jsonObj.get("response");
        JSONObject parseBody = (JSONObject) jsonObj.get("body");
        JSONArray array = (JSONArray) parseBody.get("items");

        for (Object o : array) {
            jObj = (JSONObject) o;

            Domain domain = Domain.builder()
                    .houseName(jObj.get("bldNm").toString())
                    .buildUse(jObj.get("mainPurpsCdNm").toString())
                    .buildingStructure(jObj.get("strctCdNm").toString())
                    .useAprDay(jObj.get("useAprDay").toString())
                    .grndFloor(jObj.get("grndFlrCnt").toString())
                    .ugrndFloor(jObj.get("ugrndFlrCnt").toString())
                    .elevator(jObj.get("rideUseElvtCnt").toString())
                    .build();
            apiRepository.save(domain);
        }
    }
}
