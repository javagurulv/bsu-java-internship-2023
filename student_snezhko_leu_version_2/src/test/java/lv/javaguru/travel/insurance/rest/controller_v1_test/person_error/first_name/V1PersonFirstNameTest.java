package lv.javaguru.travel.insurance.rest.controller_v1_test.person_error.first_name;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.Test;

public class V1PersonFirstNameTest extends TravelCalculatePremiumControllerTestCase {

    //first name is null
    @Test
    public void execute2() throws Exception {
        executeAndCompare("first_name is null");
    }

    //first name is empty
    @Test
    public void execute3() throws Exception {
        executeAndCompare("first_name is empty");
    }

    @Override
    protected String getPathToTestCaseFolder() {
        return "person_error/first_name/";
    }
}
