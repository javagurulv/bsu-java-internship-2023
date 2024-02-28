package lv.javaguru.travel.insurance.rest.controller_v2_test;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerV2TestCase11 extends TravelCalculatePremiumControllerV2TestCase {

    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_11";
    }
}
