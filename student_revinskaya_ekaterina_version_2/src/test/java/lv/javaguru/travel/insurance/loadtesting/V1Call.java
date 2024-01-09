package lv.javaguru.travel.insurance.loadtesting;

import com.fasterxml.jackson.core.JsonProcessingException;

public class V1Call extends CommonCall implements Runnable{

    @Override
    public void run() {
        try {
            sendRequest("rest/v1/risk_travel_cancellation/calculate_cancellation_risk_with_age_coefficient",
                    "http://localHost:8080/insurance/travel/api/v1/");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
