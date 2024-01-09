package lv.javaguru.travel.insurance.rest.v2.risks;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("case4")
public class RiskValidationWithTravelCancellationNOTEnableV2TestCases extends TravelCalculatePremiumControllerV2Test {
    @Test
    @DisplayName("request with TRAVEL_CANCELLATION selected, but not enable")
    public void testRequest() throws Exception {
        equalsJsonFiles("risks/ERROR_CODE_22_cancellation_risk_selected_and_not_enable");
    }
}
