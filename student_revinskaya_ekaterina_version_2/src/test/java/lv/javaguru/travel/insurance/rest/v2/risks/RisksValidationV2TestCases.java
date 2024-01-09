package lv.javaguru.travel.insurance.rest.v2.risks;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RisksValidationV2TestCases extends TravelCalculatePremiumControllerV2Test {

    @Test
    @DisplayName("request with empty selected risks")
    public void testRequest1() throws Exception {
        equalsJsonFiles("risks/ERROR_CODE_8_empty_selected_risks");
    }

    @Test
    @DisplayName("request with null selected risks")
    public void testRequest2() throws Exception {
        equalsJsonFiles("risks/ERROR_CODE_8_null_selected_risks");
    }

    @Test
    @DisplayName("request with not exist selected risks")
    public void testRequest3() throws Exception {
        equalsJsonFiles("risks/ERROR_CODE_9_not_exist_selected_risks");
    }

}
