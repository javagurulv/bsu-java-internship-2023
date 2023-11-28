package lv.javaguru.travel.insurance.rest.v2.risks;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RiskLevelV2TestCases extends TravelCalculatePremiumControllerV2TestCase {

    @Test
    @DisplayName("Success case with [TRAVEL_MEDICAL, TRAVEL_CANCELLATION]")
    public void executeTestCase1() throws Exception {
        executeAndCompare("test_case_1",true);
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

}
