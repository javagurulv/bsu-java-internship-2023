package lv.javaguru.travel.insurance.core.loadtesting;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.rest.common.JsonFileReader;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CommonCall {

    static void executeCall(String url, String requestFilePath, String responseFilePath, LoadTestingStatistic statistic) throws JSONException {
        JsonFileReader jsonFileReader = new JsonFileReader();
        String jsonRequest = jsonFileReader.readJsonFromFile(requestFilePath);
        String jsonResponse = jsonFileReader.readJsonFromFile(responseFilePath);
        Stopwatch stopwatch = Stopwatch.createStarted();
        executeRestAndCompareResults(jsonRequest, jsonResponse, url);
        stopwatch.stop();
        long millis = stopwatch.elapsed().toMillis();
        statistic.add(millis);
    }
    static void executeRestAndCompareResults(String requestBody, String expectedResponse, String url) throws JSONException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        String responseBodyContent = restTemplate.postForObject(url, request, String.class);

        JSONAssert.assertEquals(responseBodyContent, expectedResponse,
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("uuid", (o1, o2) -> true)));

        JSONObject jsonObject = new JSONObject(responseBodyContent);
        if (jsonObject.has("uuid")) {
            String generatedUUID = (String) jsonObject.get("uuid");
            assertThat(UUID.fromString(generatedUUID).toString()).isEqualTo(generatedUUID);

            /*assertJson(responseBodyContent)
                    .where()
                    .keysInAnyOrder()
                    .arrayInAnyOrder()
                    .at("/uuid").isNotEmpty()
                    .isEqualTo(expectedResponse);*/
        }
    }
}
