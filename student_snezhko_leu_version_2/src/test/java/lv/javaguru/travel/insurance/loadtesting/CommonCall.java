package lv.javaguru.travel.insurance.loadtesting;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.rest.JsonFileReader;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static lv.javaguru.travel.insurance.rest.RemoveRandomValues.removeRandomValues;

public class CommonCall {
    private static final JsonFileReader jsonFileReader = new JsonFileReader();
    private static final RestTemplate restTemplate = new RestTemplate();

    protected void executeAndCompare(String url, String requestPath, String responsePath, LoadTestingStatistic statistics) throws IOException, JSONException {
        String requestBody = jsonFileReader.readJsonFromFile(requestPath);
        String expectedResponseBody = jsonFileReader.readJsonFromFile(responsePath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        Stopwatch stopwatch = Stopwatch.createStarted();
        String responseBody = restTemplate.postForObject(url, httpEntity, String.class);
        stopwatch.stop();
        responseBody = removeRandomValues(responseBody);

        JSONAssert.assertEquals(expectedResponseBody, responseBody, JSONCompareMode.LENIENT);

        statistics.add(stopwatch.elapsed().toMillis());
        //return stopwatch.elapsed().toMillis();
    }
}
