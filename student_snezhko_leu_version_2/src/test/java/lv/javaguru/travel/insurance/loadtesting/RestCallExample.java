package lv.javaguru.travel.insurance.loadtesting;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.rest.JsonFileReader;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static lv.javaguru.travel.insurance.rest.RemoveRandomValues.removeRandomValues;


public class RestCallExample {

    public static void main(String[] args) {
        try {
//        executeCall("http://localhost:8080/insurance/travel/api/v2", "rest/v2/correct_request/correct request TM/request.json","rest/v2/correct_request/correct request TM/response.json");

            JsonFileReader jsonReader = new JsonFileReader();

            String urlV1 = "http://localhost:8080/insurance/travel/api/v1/";
            String urlV2 = "http://localhost:8080/insurance/travel/api/v2/";

            String requestV1Path = "rest/v1/correct_request/all fields filled/request.json";
            String responseV1Path = "rest/v1/correct_request/all fields filled/response.json";

            String requestV2Path = "rest/v2/correct_request/correct request TM/request.json";
            String responseV2Path = "rest/v2/correct_request/correct request TM/response.json";

            String requestV1Body = jsonReader.readJsonFromFile(requestV1Path);
            String expectedResponseV1Body = jsonReader.readJsonFromFile(responseV1Path);

            String requestV2Body = jsonReader.readJsonFromFile(requestV2Path);
            String expectedResponseV2Body = jsonReader.readJsonFromFile(responseV2Path);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntityV1 = new HttpEntity<>(requestV1Body, headers);
            HttpEntity<String> httpEntityV2 = new HttpEntity<>(requestV2Body, headers);

            RestTemplate restTemplate = new RestTemplate();

            Stopwatch stopwatch = Stopwatch.createStarted();
            String responseV1Body = restTemplate.postForObject(urlV1, httpEntityV1, String.class);
            stopwatch.stop();
            System.out.println("V1 :" + stopwatch.elapsed().toMillis() + " ms");
            responseV1Body = removeRandomValues(responseV1Body);

            stopwatch = Stopwatch.createStarted();
            String responseV2Body = restTemplate.postForObject(urlV2, httpEntityV2, String.class);
            stopwatch.stop();
            System.out.println("V2 : " + stopwatch.elapsed().toMillis() + " ms");
            responseV2Body = removeRandomValues(responseV2Body);

            JSONAssert.assertEquals(expectedResponseV1Body, responseV1Body, JSONCompareMode.LENIENT);
            JSONAssert.assertEquals(expectedResponseV2Body, responseV2Body, JSONCompareMode.LENIENT);
            //JSONAssert.assertEquals(expectedResponseBody, responseBody, new CustomComparator(JSONCompareMode.LENIENT, new Customization("uuid", (u1, u2) -> true)));

             }


        catch(JSONException | IOException e) {
            e.printStackTrace();
        }
    }
}