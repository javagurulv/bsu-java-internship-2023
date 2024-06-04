package lv.javaguru.travel.insurance.rest.controller_v1_test.correct_request;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.Test;

public class AllFieldFilledTest extends TravelCalculatePremiumControllerTestCase {

    @Test
    public void execute1() throws Exception {
        executeAndCompare("all fields filled");
    }

    @Override
    protected String getPathToTestCaseFolder() {
        return "correct_request/";
    }
}
