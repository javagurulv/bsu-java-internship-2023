package lv.javaguru.travel.insurance.loadtesting;

import lv.javaguru.travel.insurance.rest.common.JsonFileReader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

class RestCallExample {

    private static final String CALCULATE_PREMIUM_V1_LOCAL_URL = "http://localhost:8080/insurance/travel/api/v1/";
    private static final String CALCULATE_PREMIUM_V2_LOCAL_URL = "http://localhost:8080/insurance/travel/api/v2/";

    public static void main(String[] args) {
        JsonFileReader jsonFileReader = new JsonFileReader();

        executeV1Call(jsonFileReader);
        executeV2Call(jsonFileReader);
    }

    private static void executeV1Call(JsonFileReader jsonFileReader) {
        String jsonRequest = jsonFileReader.readJsonFromFile("rest/v1/risk_travel_medical/test_case_1/request.json");
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/v1/risk_travel_medical/test_case_1/response.json");
        executeRestCallAndCompareResults(jsonRequest, jsonResponse, CALCULATE_PREMIUM_V1_LOCAL_URL);
    }

    private static void executeV2Call(JsonFileReader jsonFileReader) {
        String jsonRequest = jsonFileReader.readJsonFromFile("rest/v2/risks/Success_TRAVEL_MEDICAL_TRAVEL_CANCELLATION/request.json");
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/v2/risks/Success_TRAVEL_MEDICAL_TRAVEL_CANCELLATION/response.json");
        executeRestCallAndCompareResults(jsonRequest, jsonResponse, CALCULATE_PREMIUM_V2_LOCAL_URL);
    }

    private static void executeRestCallAndCompareResults(String jsonRequest,
                                                         String jsonExpectedResponse,
                                                         String url) {
        var restTemplate = new RestTemplate();
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var request = new HttpEntity<String>(jsonRequest, headers);

        var responseBodyContent = restTemplate.postForObject(url, request, String.class);

        assertJson(responseBodyContent)
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .at("/uuid").isNotEmpty()
                .isEqualTo(jsonExpectedResponse);
    }

}
