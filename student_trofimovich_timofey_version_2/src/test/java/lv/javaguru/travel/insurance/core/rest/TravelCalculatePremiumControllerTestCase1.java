package lv.javaguru.travel.insurance.core.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase1 extends TravelCalculatePremiumControllerTest {
    @Override
    protected String getTestCaseFolderName() {
        return "test_case_1";
    }
    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }
}
