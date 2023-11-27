package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class TravelCalculatePremiumControllerTestCase1 extends TravelCalculatePremiumControllerTest{
    @Test
    public void testRequestWithoutFirstName() throws Exception {
        equalsJsonFiles("rest/test_case_1/ControllerRequestWithoutFirstName.json", "rest/test_case_1/ControllerResponseWithoutFirstName.json");
    }
}
