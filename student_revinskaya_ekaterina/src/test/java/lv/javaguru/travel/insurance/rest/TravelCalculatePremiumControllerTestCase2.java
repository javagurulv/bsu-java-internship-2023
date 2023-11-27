package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase2 extends TravelCalculatePremiumControllerTest{
    @Test
    public void testRequestWithoutLastName() throws Exception {
        equalsJsonFiles("rest/test_case_2/ControllerRequestWithoutLastName.json", "rest/test_case_2/ControllerResponseWithoutLastName.json");
    }
}
