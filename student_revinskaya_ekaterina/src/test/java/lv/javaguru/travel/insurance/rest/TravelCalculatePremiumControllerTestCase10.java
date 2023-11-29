package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase10 extends TravelCalculatePremiumControllerTest{
    @Test
    public void testRequestWithoutMedicalRiskLimitLevel() throws Exception {
        equalsJsonFiles("rest/test_case_10/ControllerRequestWithoutMedicalRiskLimitLevel.json",
                "rest/test_case_10/ControllerResponseWithoutMedicalRiskLimitLevel.json");
    }
}
