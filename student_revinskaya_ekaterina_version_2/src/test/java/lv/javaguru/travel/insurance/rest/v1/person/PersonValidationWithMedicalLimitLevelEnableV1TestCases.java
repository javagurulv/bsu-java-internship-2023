package lv.javaguru.travel.insurance.rest.v1.person;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("case1")
public class PersonValidationWithMedicalLimitLevelEnableV1TestCases extends TravelCalculatePremiumControllerV1Test {

    @Test
    @DisplayName("request where medical limit level null but enable, RISK_TYPE-TRAVEL_MEDICAL")
    public void testRequest1() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_14_medical_limit_level_null");
    }

    @Test
    @DisplayName("request where medical limit level not exist but enable, RISK_TYPE-TRAVEL_MEDICAL")
    public void testRequest2() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_15_medical_limit_level_not_exist");
    }
}
