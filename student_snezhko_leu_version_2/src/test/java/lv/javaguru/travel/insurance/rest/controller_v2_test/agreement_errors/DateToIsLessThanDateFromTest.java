package lv.javaguru.travel.insurance.rest.controller_v2_test.agreement_errors;

import lv.javaguru.travel.insurance.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class DateToIsLessThanDateFromTest extends TravelCalculatePremiumControllerV2TestCase {

    //dateTo less than dateFrom
    @Test
    public void execute12() throws Exception {
        executeAndCompare("date_to less than date_from");
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "agreement_error/";
    }
}
