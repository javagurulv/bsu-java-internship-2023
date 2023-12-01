package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase8 extends TravelCalculatePremiumControllerTest{
    @Test
    public void testRequestWithoutCountry() throws Exception {
        equalsJsonFiles("rest/test_case_8/ControllerRequestWithoutCountry.json", "rest/test_case_8/ControllerResponseWithoutCountry.json");
    }
}
