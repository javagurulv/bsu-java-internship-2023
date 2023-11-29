package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase6 extends TravelCalculatePremiumControllerTest{

    @Test
    public void testRequestWithDateToInPast() throws Exception {
        equalsJsonFiles("rest/test_case_6/ControllerRequestWithDateToInPast.json", "rest/test_case_6/ControllerResponseWithDateToInPast.json");
    }
}
