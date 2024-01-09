package lv.javaguru.travel.insurance.loadtesting;

import com.fasterxml.jackson.core.JsonProcessingException;

public class V2Call extends CommonCall implements Runnable{

    @Override
    public void run() {
        try {
            sendRequest("rest/v2/risk_travel_medical/calculate_medical_risk_with_age_coefficient",
                    "http://localHost:8080/insurance/travel/api/v2/");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
