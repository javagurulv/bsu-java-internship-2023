package lv.javaguru.travel.insurance.rest.v1.risks;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RisksValidationV1TestCases extends TravelCalculatePremiumControllerV1Test {
    @Test
    @DisplayName("request with null selected_risks")
    public void testRequest1() throws Exception {
        equalsJsonFiles("risks/ERROR_CODE_8_null_selected_risks");
    }

    @Test
    @DisplayName("request with empty selected_risks")
    public void testRequest2() throws Exception {
        equalsJsonFiles("risks/ERROR_CODE_8_empty_selected_risks");
    }

    @Test
    @DisplayName("request with selectedRisk is not supported by the system")
    public void testRequest3() throws Exception {
        equalsJsonFiles("risks/ERROR_CODE_9_selected_risk_not_supported_by_system");
    }
    @Test
    @DisplayName("request with selectedRisks is not supported by the system(two risks)")
    public void testRequest4() throws Exception {
        equalsJsonFiles("risks/ERROR_CODE_9_two_selected_risk_not_supported_by_system");
    }

}
