package lv.javaguru.travel.insurance.rest.controller_v2_test.agreement_errors.date_from;

import lv.javaguru.travel.insurance.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class V2AgreementDateFromTest extends TravelCalculatePremiumControllerV2TestCase {

    //date_from is null
    @Test
    public void execute8() throws Exception {
        executeAndCompare("date_from is null");
    }

    //date_from is in the past
    @Test
    public void execute10() throws Exception {
        executeAndCompare("date_from is in the past");
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "agreement_error/date_from/";
    }
}
