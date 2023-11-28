package lv.javaguru.travel.insurance.rest.v1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerV1AllTestCases extends TravelCalculatePremiumControllerV1TestCase {

    @Test
    @DisplayName("Success case with TRAVEL_MEDICAL risk only")
    public void executeTestCase1() throws Exception {
        executeAndCompare("test_case_1", true);
    }

    @Test
    @DisplayName("ERROR_CODE_7 personFirstName is NULL")
    public void executeTestCase2() throws Exception {
        executeAndCompare("test_case_2");
    }

    @Test
    @DisplayName("ERROR_CODE_7 personFirstName is empty")
    public void executeTestCase3() throws Exception {
        executeAndCompare("test_case_3");
    }

    @Test
    @DisplayName("ERROR_CODE_8 personLastName is NULL")
    public void executeTestCase4() throws Exception {
        executeAndCompare("test_case_4");
    }

    @Test
    @DisplayName("ERROR_CODE_8 personLastName is empty")
    public void executeTestCase5() throws Exception {
        executeAndCompare("test_case_5");
    }

    @Test
    @DisplayName("ERROR_CODE_11 personBirthDate is NULL")
    public void executeTestCase6() throws Exception {
        executeAndCompare("test_case_6");
    }

    @Test
    @DisplayName("ERROR_CODE_12 personBirthDate in the future")
    public void executeTestCase7() throws Exception {
        executeAndCompare("test_case_7");
    }

    @Test
    @DisplayName("ERROR_CODE_2 agreementDateFrom is NULL")
    public void executeTestCase8() throws Exception {
        executeAndCompare("test_case_8");
    }

    @Test
    @DisplayName("ERROR_CODE_4 agreementDateTo is NULL")
    public void executeTestCase9() throws Exception {
        executeAndCompare("test_case_9");
    }

    @Test
    @DisplayName("ERROR_CODE_1 agreementDateFrom must be in the future")
    public void executeTestCase10() throws Exception {
        executeAndCompare("test_case_10");
    }

    @Test
    @DisplayName("ERROR_CODE_3 agreementDateTo must be in the future")
    public void executeTestCase11() throws Exception {
        executeAndCompare("test_case_11");
    }

    @Test
    @DisplayName("ERROR_CODE_5 agreementDateFrom must be less than agreementDateTo")
    public void executeTestCase12() throws Exception {
        executeAndCompare("test_case_12");
    }

    @Test
    @DisplayName("ERROR_CODE_6 selectedRisks is NULL, must not be empty")
    public void executeTestCase13() throws Exception {
        executeAndCompare("test_case_13");
    }

    @Test
    @DisplayName("ERROR_CODE_6 selectedRisks is [], must not be empty")
    public void executeTestCase14() throws Exception {
        executeAndCompare("test_case_14");
    }

    @Test
    @DisplayName("ERROR_CODE_9 one invalid selected_risks ic not supported")
    public void executeTestCase15() throws Exception {
        executeAndCompare("test_case_15");
    }

    @Test
    @DisplayName("ERROR_CODE_9 two invalid selected_risks not supported")
    public void executeTestCase16() throws Exception {
        executeAndCompare("test_case_16");
    }

    @Test
    @DisplayName("ERROR_CODE_10 TRAVEL_MEDICAL country is empty, must not be empty")
    public void executeTestCase17() throws Exception {
        executeAndCompare("test_case_17");
    }

    @Test
    @DisplayName("ERROR_CODE_10 TRAVEL_MEDICAL country is NULL, must not be empty")
    public void executeTestCase18() throws Exception {
        executeAndCompare("test_case_18");
    }

    @Test
    @DisplayName("Multiple errors all field is NULL except selected_risks")
    public void executeTestCase19() throws Exception {
        executeAndCompare("test_case_19");
    }

    @Test
    @DisplayName("Multiple errors all field is NULL")
    public void executeTestCase20() throws Exception {
        executeAndCompare("test_case_20");
    }

    @Test
    @DisplayName("ERROR_CODE_10 TRAVEL_EVACUATION country is NULL, must not be empty")
    public void executeTestCase21() throws Exception {
        executeAndCompare("test_case_21");
    }

    @Test
    @DisplayName("ERROR_CODE_10 TRAVEL_EVACUATION country is empty, must not be empty")
    public void executeTestCase22() throws Exception {
        executeAndCompare("test_case_22");
    }

    @Test
    @DisplayName("ERROR_CODE_16 personCode is NULL, must not be empty")
    public void executeTestCase23() throws Exception {
        executeAndCompare("test_case_23");
    }

    @Test
    @DisplayName("ERROR_CODE_16 personCode is empty, must not be empty")
    public void executeTestCase24() throws Exception {
        executeAndCompare("test_case_24");
    }

    @Test
    @DisplayName("ERROR_CODE_19 travelCost is NULL, must not be empty")
    public void executeTestCase25() throws Exception {
        executeAndCompare("test_case_25");
    }

}
