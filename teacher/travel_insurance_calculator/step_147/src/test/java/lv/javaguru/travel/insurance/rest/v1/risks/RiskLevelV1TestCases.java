package lv.javaguru.travel.insurance.rest.v1.risks;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RiskLevelV1TestCases extends TravelCalculatePremiumControllerV1TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "risks";

    @Test
    @DisplayName("ERROR_CODE_6 selectedRisks is NULL, must not be empty")
    public void executeTestCase13() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_13");
    }

    @Test
    @DisplayName("ERROR_CODE_6 selectedRisks is [], must not be empty")
    public void executeTestCase14() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_14");
    }

    @Test
    @DisplayName("ERROR_CODE_9 one invalid selected_risks ic not supported")
    public void executeTestCase15() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_15");
    }

    @Test
    @DisplayName("ERROR_CODE_9 two invalid selected_risks not supported")
    public void executeTestCase16() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_16");
    }

}
