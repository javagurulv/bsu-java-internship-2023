package lv.javaguru.travel.insurance.rest.controller_v1_test.person_error.birth_date;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.Test;

public class V1PersonBirthDateTest extends TravelCalculatePremiumControllerTestCase {

    //birth_date is in the future
    @Test
    public void execute7() throws Exception {
        executeAndCompare("birth_date is in the future");
    }

    //birth_date is null
    @Test
    public void execute6() throws Exception {
        executeAndCompare("birth_date is null");
    }

    @Override
    protected String getPathToTestCaseFolder() {
        return "person_error/birth_date/";
    }
}
