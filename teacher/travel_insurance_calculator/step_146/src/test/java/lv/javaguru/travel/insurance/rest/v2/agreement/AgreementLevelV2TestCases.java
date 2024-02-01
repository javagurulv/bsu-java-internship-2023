package lv.javaguru.travel.insurance.rest.v2.agreement;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementLevelV2TestCases extends TravelCalculatePremiumControllerV2TestCase {

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


}
