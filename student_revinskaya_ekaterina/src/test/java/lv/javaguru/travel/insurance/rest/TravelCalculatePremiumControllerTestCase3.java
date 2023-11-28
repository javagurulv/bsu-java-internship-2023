package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase3 extends TravelCalculatePremiumControllerTest{

    @Test
    public void testRequestWithoutDateFrom() throws Exception {
        equalsJsonFiles("rest/test_case_3/ControllerRequestWithoutDateFrom.json", "rest/test_case_3/ControllerResponseWithoutDateFrom.json");
    }
}
