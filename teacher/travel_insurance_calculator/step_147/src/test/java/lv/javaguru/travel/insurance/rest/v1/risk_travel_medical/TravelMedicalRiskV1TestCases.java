package lv.javaguru.travel.insurance.rest.v1.risk_travel_medical;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelMedicalRiskV1TestCases extends TravelCalculatePremiumControllerV1TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "risk_travel_medical";

    @Test
    @DisplayName("Success case with TRAVEL_MEDICAL risk only")
    public void executeTestCase1() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_1", true);
    }

    // TODO Add tests for medicalRiskLimitLevel field

}
