package lv.javaguru.travel.insurance.rest.v2.risk_travel_medical;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelMedicalRiskV2TestCases extends TravelCalculatePremiumControllerV2TestCase {

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
    @DisplayName("ERROR_CODE_19 two persons travelCost is NULL, must not be empty")
    public void executeTestCase36() throws Exception {
        executeAndCompare("test_case_36");
    }

}
