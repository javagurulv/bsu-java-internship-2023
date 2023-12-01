package lv.javaguru.travel.insurance.loadtesting;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.rest.common.JsonFileReader;

class V2Call extends CommonCall implements Runnable {

    private static final String CALCULATE_PREMIUM_V2_LOCAL_URL = "http://localhost:8080/insurance/travel/api/v2/";
    private String jsonRequest;
    private String jsonResponse;
    private LoadTestingStatistic statistic;

    private Stopwatch stopwatch;

    public V2Call(LoadTestingStatistic statistic) {
        this.statistic = statistic;
        JsonFileReader jsonFileReader = new JsonFileReader();
        jsonRequest = jsonFileReader.readJsonFromFile("rest/v2/risks/Success_TRAVEL_MEDICAL_TRAVEL_CANCELLATION/request.json");
        jsonResponse = jsonFileReader.readJsonFromFile("rest/v2/risks/Success_TRAVEL_MEDICAL_TRAVEL_CANCELLATION/response.json");
        stopwatch = Stopwatch.createStarted();
    }

    @Override
    public void run() {
        executeRestCallAndCompareResults(jsonRequest, jsonResponse, CALCULATE_PREMIUM_V2_LOCAL_URL);
        stopwatch.stop();
        long elapsedMillis = stopwatch.elapsed().toMillis();
        System.out.println("Request processing time (ms): " + elapsedMillis);
        statistic.addExecutionTime(elapsedMillis);
    }

}
