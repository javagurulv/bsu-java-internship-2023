package lv.javaguru.travel.insurance.core.rest.v2.risk_travel_cancellation;

import lv.javaguru.travel.insurance.core.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCancellationRiskV2TestCases extends TravelCalculatePremiumControllerV2TestCase {
    private static final String TEST_FILE_BASE_FOLDER = "risk_travel_cancellation";
    @DisplayName("no error, medical risk limit level is empty, RISK_TYPE - TRAVEL_CANCELLATION")
    @Test
    public void executeTestCase29() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/no error, medical risk limit level is empty, RISK_TYPE - TRAVEL_CANCELLATION");
    }
    @DisplayName("ERROR_CODE_19=Field travelCost is empty, RISK_TYPE - TRAVEL_CANCELLATION")
    @Test
    public void executeTestCase31() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_19=Field travelCost is empty, RISK_TYPE - TRAVEL_CANCELLATION");
    }
}
