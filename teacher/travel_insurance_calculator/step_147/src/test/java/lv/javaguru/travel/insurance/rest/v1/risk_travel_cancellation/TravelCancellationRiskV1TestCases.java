package lv.javaguru.travel.insurance.rest.v1.risk_travel_cancellation;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCancellationRiskV1TestCases extends TravelCalculatePremiumControllerV1TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "risk_travel_cancellation";

    @Test
    @DisplayName("ERROR_CODE_19 travelCost is NULL, must not be empty")
    public void executeTestCase25() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_25");
    }

}
