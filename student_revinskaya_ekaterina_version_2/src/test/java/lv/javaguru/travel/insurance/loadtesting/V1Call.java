package lv.javaguru.travel.insurance.loadtesting;

import com.fasterxml.jackson.core.JsonProcessingException;

public class V1Call extends CommonCall implements Runnable {
    private LoadTestingStatistic loadTestingStatistic;

    V1Call(LoadTestingStatistic loadTestingStatistic) {
        this.loadTestingStatistic = loadTestingStatistic;
    }

    @Override
    public void run() {
        try {
            Long executionTime = sendRequest("rest/v1/risk_travel_cancellation/calculate_cancellation_risk_with_age_coefficient",
                    "http://localHost:8080/insurance/travel/api/v1/");
            loadTestingStatistic.add(executionTime);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
