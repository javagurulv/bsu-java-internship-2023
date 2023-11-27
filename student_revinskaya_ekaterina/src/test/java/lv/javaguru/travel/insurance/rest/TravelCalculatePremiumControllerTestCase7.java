package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase7 extends TravelCalculatePremiumControllerTest{
    @Test
    public void testRequestWithDateFromMoreThanDateTo() throws Exception {
        equalsJsonFiles("rest/test_case_7/ControllerRequestWithDateFromMoreThanTo.json",
                "rest/test_case_7/ControllerResponseWithDateFromMoreThanTo.json");
    }
}
