package lv.javaguru.travel.insurance.loadtesting;

import com.fasterxml.jackson.core.JsonProcessingException;

public class V2Call extends CommonCall implements Runnable{
    private LoadTestingStatistic loadTestingStatistic;

    V2Call(LoadTestingStatistic loadTestingStatistic) {
        this.loadTestingStatistic = loadTestingStatistic;
    }
    @Override
    public void run() {
        try {
            Long executionTime = sendRequest("rest/v2/risk_travel_medical/calculate_medical_risk_with_age_coefficient",
                    "http://localHost:8080/insurance/travel/api/v2/");
            loadTestingStatistic.add(executionTime);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
