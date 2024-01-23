package lv.javaguru.travel.insurance.rest.v1.risk_travel_medical;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("case1")
public class TravelMedicalWithAgeCoefficientEnableV1TestCases extends TravelCalculatePremiumControllerV1Test {
    @Test
    @DisplayName("response with age coefficient enable")
    public void testRequest() throws Exception {
        equalsJsonFiles("risk_travel_medical/calculate_medical_risk_with_age_coefficient", true);
    }

}
