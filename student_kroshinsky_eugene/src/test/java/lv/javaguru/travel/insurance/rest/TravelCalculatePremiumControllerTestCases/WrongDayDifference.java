package lv.javaguru.travel.insurance.rest.TravelCalculatePremiumControllerTestCases;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumControllerTest;
import org.junit.jupiter.api.Test;

public class WrongDayDifference extends TravelCalculatePremiumControllerTest {

    @Test
    public void doTest() throws Exception {
        Test();
    }

    @Override
    protected String getPath() {
        return "test_case_wrong_day_difference";
    }
}
