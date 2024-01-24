package lv.javaguru.travel.insurance.core.rest.v1;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerV1TestCase2 extends TravelCalculatePremiumControllerV1Test {
    @Override
    protected String getTestCaseFolderName() {
        return "test_case_2";
    }
    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }
}
