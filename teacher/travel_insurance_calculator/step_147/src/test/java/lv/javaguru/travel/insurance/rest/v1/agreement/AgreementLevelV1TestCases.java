package lv.javaguru.travel.insurance.rest.v1.agreement;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementLevelV1TestCases extends TravelCalculatePremiumControllerV1TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "agreement";

    @Test
    @DisplayName("ERROR_CODE_2 agreementDateFrom is NULL")
    public void executeTestCase8() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_8");
    }

    @Test
    @DisplayName("ERROR_CODE_4 agreementDateTo is NULL")
    public void executeTestCase9() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_9");
    }

    @Test
    @DisplayName("ERROR_CODE_1 agreementDateFrom must be in the future")
    public void executeTestCase10() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_10");
    }

    @Test
    @DisplayName("ERROR_CODE_3 agreementDateTo must be in the future")
    public void executeTestCase11() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_11");
    }

    @Test
    @DisplayName("ERROR_CODE_5 agreementDateFrom must be less than agreementDateTo")
    public void executeTestCase12() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_12");
    }

    @Test
    @DisplayName("ERROR_CODE_10 TRAVEL_MEDICAL country is empty, must not be empty")
    public void executeTestCase17() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_17");
    }

    @Test
    @DisplayName("ERROR_CODE_10 TRAVEL_MEDICAL country is NULL, must not be empty")
    public void executeTestCase18() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_18");
    }

    @Test
    @DisplayName("Multiple errors all field is NULL except selected_risks")
    public void executeTestCase19() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_19");
    }

    @Test
    @DisplayName("Multiple errors all field is NULL")
    public void executeTestCase20() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_20");
    }

    @Test
    @DisplayName("ERROR_CODE_10 TRAVEL_EVACUATION country is NULL, must not be empty")
    public void executeTestCase21() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_21");
    }

    @Test
    @DisplayName("ERROR_CODE_10 TRAVEL_EVACUATION country is empty, must not be empty")
    public void executeTestCase22() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_22");
    }

}
