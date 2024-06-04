package lv.javaguru.travel.insurance.rest.controller_v1_test.agreement_error;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateToLessThanDateFromTest extends TravelCalculatePremiumControllerTestCase {

    @DisplayName("Date_to less than date_from test")
    @Test
    public void execute12() throws Exception {
        executeAndCompare("date_to less than date_from");
    }

    @Override
    protected String getPathToTestCaseFolder() {
        return "agreement_error/";
    }
}
