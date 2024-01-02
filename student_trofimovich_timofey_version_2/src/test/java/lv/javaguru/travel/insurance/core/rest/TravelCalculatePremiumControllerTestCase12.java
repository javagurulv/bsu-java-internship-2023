package lv.javaguru.travel.insurance.core.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase12 extends TravelCalculatePremiumControllerTest {
    @Override
    protected String getTestCaseFolderName() {
        return "test_case_12";
    }
    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }
}
