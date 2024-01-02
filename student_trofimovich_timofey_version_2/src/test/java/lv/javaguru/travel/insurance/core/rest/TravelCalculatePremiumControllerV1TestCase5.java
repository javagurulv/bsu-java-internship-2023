package lv.javaguru.travel.insurance.core.rest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerV1TestCase5 extends TravelCalculatePremiumControllerV1Test {
    @Override
    protected String getTestCaseFolderName() {
        return "test_case_5";
    }
    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }
}
