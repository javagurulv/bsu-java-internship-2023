package lv.javaguru.travel.insurance.rest.controller_v1_test.without_all_fields;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.Test;

public class RequestWithoutFieldsTest extends TravelCalculatePremiumControllerTestCase {

    //without any field
    @Test
    public void execute20() throws Exception {
        executeAndCompare("all fields not provided");
    }

    //without any field, but selected risk is travel_medical
    @Test
    public void execute19() throws Exception {
        executeAndCompare("all fields not provided TM");
    }

    @Override
    protected String getPathToTestCaseFolder() {
        return "without_all_fields/";
    }
}
