package lv.javaguru.travel.insurance.rest.v1.person;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("case2")
public class PersonValidationWithMedicalLimitLevelNOTEnableV1TestCases extends TravelCalculatePremiumControllerV1Test {

    @Test
    @DisplayName("request where medical limit level specified but not enable, RISK_TYPE-TRAVEL_MEDICAL")
    public void testRequest25() throws Exception {
        equalsJsonFiles("test_case_25");
    }

}
