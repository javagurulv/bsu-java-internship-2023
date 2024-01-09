package lv.javaguru.travel.insurance.loadtesting;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

@Component
public class RestCallExample {
    private static final String BASE_URL = "http://localHost:8080/insurance/travel/api/";
    private static RestRequestSender restRequestSender = new RestRequestSender();

    public static void main(String[] args) throws JsonProcessingException {
        String testCaseV1 = "rest/v1/risk_travel_cancellation/calculate_cancellation_risk_with_age_coefficient";
        String testCaseV2 = "rest/v2/risk_travel_medical/calculate_medical_risk_with_age_coefficient";

        restRequestSender.sendRequest(testCaseV1, BASE_URL + "v1/");
        restRequestSender.sendRequest(testCaseV2, BASE_URL + "v2/");
    }


}
