package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase9 extends TravelCalculatePremiumControllerTest{
    @Test
    public void testRequestWithNotExistCountry() throws Exception {
        equalsJsonFiles("rest/test_case_9/ControllerRequestWithNotExistCountry.json",
                "rest/test_case_9/ControllerResponseWithNotExistCountry.json");
    }
}
