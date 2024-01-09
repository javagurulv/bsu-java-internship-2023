package lv.javaguru.travel.insurance.rest.v2.person;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1Test;
import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("case2")
public class PersonValidationWithMedicalLimitLevelNOTEnableV2TestCases extends TravelCalculatePremiumControllerV2Test {

    @Test
    @DisplayName("request where medical limit level specified but not enable, RISK_TYPE-TRAVEL_MEDICAL")
    public void testRequest() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_23_medical_limit_level_specified");
    }

}
