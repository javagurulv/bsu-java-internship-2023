package lv.javaguru.travel.insurance.core.postSendTest;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase8 extends TravelCalculatePremiumControllerTestCase {

    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_8";
    }
}
