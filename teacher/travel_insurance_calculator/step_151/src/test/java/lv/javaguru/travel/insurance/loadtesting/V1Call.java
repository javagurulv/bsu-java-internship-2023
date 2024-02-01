package lv.javaguru.travel.insurance.loadtesting;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.rest.common.JsonFileReader;

class V1Call extends CommonCall implements Runnable {

    private static final String CALCULATE_PREMIUM_V1_LOCAL_URL = "http://localhost:8080/insurance/travel/api/v1/";
    private JsonFileReader jsonFileReader = new JsonFileReader();

    @Override
    public void run() {
        String jsonRequest = jsonFileReader.readJsonFromFile("rest/v1/risk_travel_medical/test_case_1/request.json");
        String jsonResponse = jsonFileReader.readJsonFromFile("rest/v1/risk_travel_medical/test_case_1/response.json");
        Stopwatch stopwatch = Stopwatch.createStarted();
        executeRestCallAndCompareResults(jsonRequest, jsonResponse, CALCULATE_PREMIUM_V1_LOCAL_URL);
        stopwatch.stop();
        long elapsedMillis = stopwatch.elapsed().toMillis();
        System.out.println("Request processing time (ms): " + elapsedMillis);
    }

}
