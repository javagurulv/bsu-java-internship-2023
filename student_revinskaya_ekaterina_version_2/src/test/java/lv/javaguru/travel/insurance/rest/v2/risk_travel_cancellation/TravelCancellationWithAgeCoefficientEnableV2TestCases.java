package lv.javaguru.travel.insurance.rest.v2.risk_travel_cancellation;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("case1")
public class TravelCancellationWithAgeCoefficientEnableV2TestCases extends TravelCalculatePremiumControllerV2Test {
    @Test
    @DisplayName("response with age coefficient enable")
    public void testRequest30() throws Exception {
        equalsJsonFiles("test_case_30", true);
    }

}