package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase4 extends TravelCalculatePremiumControllerTest{

    @Test
    public void testRequestWithDateFromInPast() throws Exception {
        equalsJsonFiles("rest/test_case_4/ControllerRequestWithDateFromInPast.json", "rest/test_case_4/ControllerResponseWithDateFromInPast.json");
    }
}
