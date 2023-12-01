package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase5 extends TravelCalculatePremiumControllerTest{

    @Test
    public void testRequestWithoutDateTo() throws Exception {
        equalsJsonFiles("rest/test_case_5/ControllerRequestWithoutDateTo.json", "rest/test_case_5/ControllerResponseWithoutDateTo.json");
    }
}
