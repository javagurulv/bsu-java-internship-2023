package lv.javaguru.travel.insurance.rest.v1.risks;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("case4")
public class RiskValidationWithTravelCancellationNOTEnableV1TestCases extends TravelCalculatePremiumControllerV1Test {
    @Test
    @DisplayName("request with TRAVEL_CANCELLATION selected, but not enable")
    public void testRequest28() throws Exception {
        equalsJsonFiles("risks/test_case_28");
    }
}
