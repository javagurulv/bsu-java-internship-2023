package lv.javaguru.travel.insurance.rest.TravelCalculatePremiumControllerTestCases;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumControllerTest;
import org.junit.jupiter.api.Test;

class WithoutErrors extends TravelCalculatePremiumControllerTest {

    @Test
    void doTest() throws Exception {
        Test();
    }

    @Override
    protected String getPath() {
        return "test_case_without_errors";
    }

}