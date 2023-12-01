package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase14 extends TravelCalculatePremiumControllerTest{

    @Test
    public void testRequestWithoutErrors() throws Exception {
        equalsJsonFiles("rest/test_case_14/ControllerRequestWithoutErrors.json", "rest/test_case_14/ControllerResponseWithoutErrors.json");
    }
}
