package lv.javaguru.travel.insurance.core.rest.v2;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerV2TestCase6 extends TravelCalculatePremiumControllerV2Test {
    @Override
    protected String getTestCaseFolderName() {
        return "test_case_6";
    }
    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }
}
