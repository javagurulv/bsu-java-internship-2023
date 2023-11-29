package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase11 extends TravelCalculatePremiumControllerTest{
    @Test
    public void testRequestWithNotExistMedicalRiskLimitLevel() throws Exception {
        equalsJsonFiles("rest/test_case_11/ControllerRequestWithNotExistMedicalRiskLimitLevel.json",
                "rest/test_case_11/ControllerResponseWithNotExistMedicalRiskLimitLevel.json");
    }
}
