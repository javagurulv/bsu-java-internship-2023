package lv.javaguru.travel.insurance.rest.controller_v1_test.person_error.first_name;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.Test;

public class V1PersonFirstNameTest extends TravelCalculatePremiumControllerTestCase {

    //first name is null
    @Test
    public void execute2() throws Exception {
        executeAndCompare("test_case_2");
    }

    //first name is empty
    @Test
    public void execute3() throws Exception {
        executeAndCompare("test_case_3");
    }

    @Override
    protected String getPathToTestCaseFolder() {
        return "person_error/first_name/";
    }
}
