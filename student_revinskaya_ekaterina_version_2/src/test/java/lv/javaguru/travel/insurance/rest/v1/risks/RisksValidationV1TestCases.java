package lv.javaguru.travel.insurance.rest.v1.risks;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RisksValidationV1TestCases extends TravelCalculatePremiumControllerV1Test {
    @Test
    @DisplayName("request with null selected_risks")
    public void testRequest16() throws Exception {
        equalsJsonFiles("risks/test_case_16");
    }

    @Test
    @DisplayName("request with empty selected_risks")
    public void testRequest17() throws Exception {
        equalsJsonFiles("risks/test_case_17");
    }

    @Test
    @DisplayName("request with selectedRisk is not supported by the system")
    public void testRequest18() throws Exception {
        equalsJsonFiles("risks/test_case_18");
    }
    @Test
    @DisplayName("request with selectedRisks is not supported by the system(two risks)")
    public void testRequest19() throws Exception {
        equalsJsonFiles("risks/test_case_19");
    }

}
