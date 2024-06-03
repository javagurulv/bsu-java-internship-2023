package lv.javaguru.travel.insurance.rest.controller_v2_test.agreement_errors.date_to;

import lv.javaguru.travel.insurance.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class V2AgreementDateToTest extends TravelCalculatePremiumControllerV2TestCase {

    //date_to is null
    @Test
    public void execute9() throws Exception {
        executeAndCompare("test_case_9");
    }

    //date_to is in the past
    @Test
    public void execute11() throws Exception {
        executeAndCompare("test_case_11");
    }

}
