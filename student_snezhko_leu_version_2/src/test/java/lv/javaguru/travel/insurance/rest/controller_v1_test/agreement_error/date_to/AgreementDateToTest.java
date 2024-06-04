package lv.javaguru.travel.insurance.rest.controller_v1_test.agreement_error.date_to;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementDateToTest extends TravelCalculatePremiumControllerTestCase {

    @DisplayName("agreement_date_to is null test")
    @Test
    public void execute9() throws Exception {
        executeAndCompare("date_to is null");
    }

    @DisplayName("agreement_date_to is in the past test")
    @Test
    public void execute11() throws Exception {
        executeAndCompare("date_to is in the past");
    }

    @Override
    protected String getPathToTestCaseFolder() {
        return "agreement_error/date_to/";
    }
}
