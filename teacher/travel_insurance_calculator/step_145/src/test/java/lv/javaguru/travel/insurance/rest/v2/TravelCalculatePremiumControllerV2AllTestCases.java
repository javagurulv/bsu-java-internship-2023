package lv.javaguru.travel.insurance.rest.v2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerV2AllTestCases extends TravelCalculatePremiumControllerV2TestCase {

    @Test
    @DisplayName("Success case with [TRAVEL_MEDICAL, TRAVEL_CANCELLATION]")
    public void executeTestCase1() throws Exception {
        executeAndCompare("test_case_1",true);
    }

    @Test
    @DisplayName("ERROR_CODE_7 one personFirstName is NULL, must not be empty")
    public void executeTestCase2() throws Exception {
        executeAndCompare("test_case_2");
    }

    @Test
    @DisplayName("ERROR_CODE_7 one personFirstName is empty, must not be empty")
    public void executeTestCase3() throws Exception {
        executeAndCompare("test_case_3");
    }

    @Test
    @DisplayName("ERROR_CODE_7 two personFirstName is empty, must not be empty")
    public void executeTestCase4() throws Exception {
        executeAndCompare("test_case_4");
    }

    @Test
    @DisplayName("ERROR_CODE_7 two personFirstName is NULL, must not be empty")
    public void executeTestCase5() throws Exception {
        executeAndCompare("test_case_5");
    }

    @Test
    @DisplayName("ERROR_CODE_8 one personLastName is empty, must not be empty")
    public void executeTestCase6() throws Exception {
        executeAndCompare("test_case_6");
    }

    @Test
    @DisplayName("ERROR_CODE_8 one personLastName is NULL, must not be empty")
    public void executeTestCase7() throws Exception {
        executeAndCompare("test_case_7");
    }

    @Test
    @DisplayName("ERROR_CODE_8 two personLastName is NULL, must not be empty")
    public void executeTestCase8() throws Exception {
        executeAndCompare("test_case_8");
    }

    @Test
    @DisplayName("ERROR_CODE_8 two personLastName is empty, must not be empty")
    public void executeTestCase9() throws Exception {
        executeAndCompare("test_case_9");
    }

    @Test
    @DisplayName("ERROR_CODE_11 one personBirthDate is NULL, must not be empty")
    public void executeTestCase10() throws Exception {
        executeAndCompare("test_case_10");
    }

    @Test
    @DisplayName("ERROR_CODE_11 two personBirthDate is NULL, must not be empty")
    public void executeTestCase11() throws Exception {
        executeAndCompare("test_case_11");
    }

    @Test
    @DisplayName("ERROR_CODE_12 one personBirthDate must be in the past")
    public void executeTestCase12() throws Exception {
        executeAndCompare("test_case_12");
    }

    @Test
    @DisplayName("ERROR_CODE_12 two personBirthDate must be in the past")
    public void executeTestCase13() throws Exception {
        executeAndCompare("test_case_13");
    }

    @Test
    @DisplayName("ERROR_CODE_2 agreementDateFrom must not be empty")
    public void executeTestCase14() throws Exception {
        executeAndCompare("test_case_14");
    }

    @Test
    @DisplayName("ERROR_CODE_4 agreementDateTo must not be empty")
    public void executeTestCase15() throws Exception {
        executeAndCompare("test_case_15");
    }

    @Test
    @DisplayName("ERROR_CODE_1 agreementDateFrom must be in the future")
    public void executeTestCase16() throws Exception {
        executeAndCompare("test_case_16");
    }

    @Test
    @DisplayName("ERROR_CODE_3 agreementDateTo must be in the future")
    public void executeTestCase17() throws Exception {
        executeAndCompare("test_case_17");
    }

    @Test
    @DisplayName("ERROR_CODE_5 agreementDateFrom must be less than agreementDateTo")
    public void executeTestCase18() throws Exception {
        executeAndCompare("test_case_18");
    }

    @Test
    @DisplayName("ERROR_CODE_6 selectedRisks is NULL, must not be empty")
    public void executeTestCase19() throws Exception {
        executeAndCompare("test_case_19");
    }

    @Test
    @DisplayName("ERROR_CODE_6 selectedRisks is [], must not be empty")
    public void executeTestCase20() throws Exception {
        executeAndCompare("test_case_20");
    }

    @Test
    @DisplayName("ERROR_CODE_9 one selectedRisks is not supported")
    public void executeTestCase21() throws Exception {
        executeAndCompare("test_case_21");
    }

    @Test
    @DisplayName("ERROR_CODE_9 two selectedRisks is not supported")
    public void executeTestCase22() throws Exception {
        executeAndCompare("test_case_22");
    }

    @Test
    @DisplayName("ERROR_CODE_10 country is NULL, must not be empty")
    public void executeTestCase23() throws Exception {
        executeAndCompare("test_case_23");
    }

    @Test
    @DisplayName("ERROR_CODE_10 country is empty, must not be empty")
    public void executeTestCase24() throws Exception {
        executeAndCompare("test_case_24");
    }

    @Test
    @DisplayName("ERROR_CODE_15 country not supported")
    public void executeTestCase25() throws Exception {
        executeAndCompare("test_case_25");
    }

    @Test
    @DisplayName("ERROR_CODE_13 one person medicalRiskLimitLevel is NULL [TRAVEL_MEDICAL], must not be empty")
    public void executeTestCase26() throws Exception {
        executeAndCompare("test_case_26");
    }

    @Test
    @DisplayName("ERROR_CODE_13 one person medicalRiskLimitLevel is empty [TRAVEL_MEDICAL], must not be empty")
    public void executeTestCase27() throws Exception {
        executeAndCompare("test_case_27");
    }

    @Test
    @DisplayName("ERROR_CODE_13 two persons medicalRiskLimitLevel is NULL [TRAVEL_MEDICAL], must not be empty")
    public void executeTestCase28() throws Exception {
        executeAndCompare("test_case_28");
    }

    @Test
    @DisplayName("ERROR_CODE_13 two persons medicalRiskLimitLevel is empty [TRAVEL_MEDICAL], must not be empty")
    public void executeTestCase29() throws Exception {
        executeAndCompare("test_case_29");
    }

    @Test
    @DisplayName("ERROR_CODE_14 one person medicalRiskLimitLevel is not supported [TRAVEL_MEDICAL]")
    public void executeTestCase30() throws Exception {
        executeAndCompare("test_case_30");
    }

    @Test
    @DisplayName("ERROR_CODE_14 two persons medicalRiskLimitLevel is not supported [TRAVEL_MEDICAL]")
    public void executeTestCase31() throws Exception {
        executeAndCompare("test_case_31");
    }

    @Test
    @DisplayName("ERROR_CODE_16 one person personCode is NULL, must not be empty")
    public void executeTestCase32() throws Exception {
        executeAndCompare("test_case_32");
    }

    @Test
    @DisplayName("ERROR_CODE_16 one person personCode is empty, must not be empty")
    public void executeTestCase33() throws Exception {
        executeAndCompare("test_case_33");
    }

    @Test
    @DisplayName("ERROR_CODE_16 two persons personCode is NULL, must not be empty")
    public void executeTestCase34() throws Exception {
        executeAndCompare("test_case_34");
    }

    @Test
    @DisplayName("ERROR_CODE_16 two persons personCode is empty, must not be empty")
    public void executeTestCase35() throws Exception {
        executeAndCompare("test_case_35");
    }

    @Test
    @DisplayName("ERROR_CODE_19 two persons travelCost is NULL, must not be empty")
    public void executeTestCase36() throws Exception {
        executeAndCompare("test_case_36");
    }

}
