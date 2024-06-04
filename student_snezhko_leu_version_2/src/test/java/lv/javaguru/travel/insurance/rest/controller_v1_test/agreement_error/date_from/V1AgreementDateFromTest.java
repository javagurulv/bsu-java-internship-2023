package lv.javaguru.travel.insurance.rest.controller_v1_test.agreement_error.date_from;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class V1AgreementDateFromTest extends TravelCalculatePremiumControllerTestCase {

    @DisplayName("Date from is in the past test")
    @Test
    public void execute10() throws Exception {
        executeAndCompare("date_from is in the past");
    }

    @DisplayName("Date from is null test")
    @Test
    public void execute8() throws Exception {
        executeAndCompare("date_from is null");
    }

    @Override
    protected String getPathToTestCaseFolder() {
        return "agreement_error/date_from/";
    }
}
