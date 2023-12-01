package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase12 extends TravelCalculatePremiumControllerTest{
    @Test
    public void testRequestWithoutSelectedRisks() throws Exception {
        equalsJsonFiles("rest/test_case_12/ControllerRequestWithoutSelectedRisks.json",
                "rest/test_case_12/ControllerResponseWithoutSelectedRisks.json");
    }
}
