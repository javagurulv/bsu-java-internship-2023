package lv.javaguru.travel.insurance.rest.controller_v2_test.agreement_errors.country;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import lv.javaguru.travel.insurance.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class V2AgreementCountryTest extends TravelCalculatePremiumControllerV2TestCase {

    //Country is empty, selected risk - TRAVEL_MEDICAL
    @Test
    public void execute17() throws Exception {
        executeAndCompare("test_case_17");
    }

    //Country is null, selected risk - TRAVEL_MEDICAL
    @Test
    public void execute18() throws Exception {
        executeAndCompare("test_case_18");
    }

    //country is not supported
    @Test
    public void execute21() throws Exception {
        executeAndCompare("test_case_21");
    }

    //Country is empty, selected risk - TRAVEL_EVACUATION
    @Test
    public void execute22() throws Exception {
        executeAndCompare("test_case_22");
    }
}
