package lv.javaguru.travel.insurance.rest.v2.risks;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RisksValidationV2TestCases extends TravelCalculatePremiumControllerV2Test {

    @Test
    @DisplayName("request with empty selected risks")
    public void testRequest12() throws Exception {
        equalsJsonFiles("test_case_12");
    }

    @Test
    @DisplayName("request with null selected risks")
    public void testRequest13() throws Exception {
        equalsJsonFiles("test_case_13");
    }

    @Test
    @DisplayName("request with not exist selected risks")
    public void testRequest14() throws Exception {
        equalsJsonFiles("test_case_14");
    }

}
