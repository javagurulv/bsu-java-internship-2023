package lv.javaguru.travel.insurance.rest.controller_v1_test.person_error.birth_date;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.Test;

public class V1PersonBirthDateTest extends TravelCalculatePremiumControllerTestCase {

    //birth_date is in the future
    @Test
    public void execute7() throws Exception {
        executeAndCompare("test_case_7");
    }

    //birth_date is null
    @Test
    public void execute6() throws Exception {
        executeAndCompare("test_case_6");
    }

    @Override
    protected String getPathToTestCaseFolder() {
        return "person_error/birth_date/";
    }
}
