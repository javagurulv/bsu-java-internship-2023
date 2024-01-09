package lv.javaguru.travel.insurance.rest.v2.risk_travel_cancellation;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("case3")

public class TravelCancellationWithAgeCoefficientNOTEnableV2TestCases extends TravelCalculatePremiumControllerV2Test {
    @Test
    @DisplayName("response with age coefficient not enable")
    public void testRequest() throws Exception {
        equalsJsonFiles("risk_travel_cancellation/calculate_cancellation_risk_WITHOUT_age_coefficient", true);
    }

}