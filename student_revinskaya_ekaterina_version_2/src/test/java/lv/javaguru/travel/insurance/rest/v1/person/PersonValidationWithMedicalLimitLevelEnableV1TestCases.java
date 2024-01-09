package lv.javaguru.travel.insurance.rest.v1.person;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("case1")
public class PersonValidationWithMedicalLimitLevelEnableV1TestCases extends TravelCalculatePremiumControllerV1Test {

    @Test
    @DisplayName("request where medical limit level null but enable, RISK_TYPE-TRAVEL_MEDICAL")
    public void testRequest23() throws Exception {
        equalsJsonFiles("person/test_case_23");
    }

    @Test
    @DisplayName("request where medical limit level not exist but enable, RISK_TYPE-TRAVEL_MEDICAL")
    public void testRequest24() throws Exception {
        equalsJsonFiles("person/test_case_24");
    }
}
