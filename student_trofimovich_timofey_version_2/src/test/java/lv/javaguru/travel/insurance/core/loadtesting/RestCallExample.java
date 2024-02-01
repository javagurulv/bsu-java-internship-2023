package lv.javaguru.travel.insurance.core.loadtesting;

import lombok.SneakyThrows;
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

class RestCallExample {

    private static final String CALCULATE_PREMIUM_V1_LOCAL_URL = "http://localhost:8080/insurance/travel/api/v1/";
    private static final String CALCULATE_PREMIUM_V2_LOCAL_URL = "http://localhost:8080/insurance/travel/api/v2/";

    @SneakyThrows
    public static void main(String[] args) {
        JsonFileReader jsonFileReader = new JsonFileReader();
        executeV1Call(jsonFileReader);
        executeV2Call(jsonFileReader);
    }

    private static void executeV2Call(JsonFileReader jsonFileReader) throws JSONException {
        String jsonRequest = jsonFileReader.readJsonFromFile("rest/v2/agreement/no error, all fields provided/request.json");
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/v2/agreement/no error, all fields provided/response.json");
        executeRestAndCompareResults(jsonRequest, jsonResponse, CALCULATE_PREMIUM_V2_LOCAL_URL);
    }

    private static void executeV1Call(JsonFileReader jsonFileReader) throws JSONException {
        String jsonRequest = jsonFileReader.readJsonFromFile("rest/v1/agreement/no error, all fields provided/request.json");
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/v1/agreement/no error, all fields provided/response.json");
        executeRestAndCompareResults(jsonRequest, jsonResponse, CALCULATE_PREMIUM_V1_LOCAL_URL);
    }

    private static void executeRestAndCompareResults(String requestBody, String expectedResponse, String url) throws JSONException {

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
