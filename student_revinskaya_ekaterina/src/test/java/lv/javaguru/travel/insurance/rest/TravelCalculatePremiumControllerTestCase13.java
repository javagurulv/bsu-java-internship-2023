package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase13 extends TravelCalculatePremiumControllerTest{
    @Test
    public void testRequestWithNoFields() throws Exception {
        equalsJsonFiles("rest/test_case_13/ControllerRequestWithNoFields.json", "rest/test_case_13/ControllerResponseWithNoFields.json");
    }
}
