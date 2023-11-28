package lv.javaguru.travel.insurance.rest.v1.person;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonLevelV1TestCases extends TravelCalculatePremiumControllerV1TestCase {

    @Test
    @DisplayName("ERROR_CODE_7 personFirstName is NULL")
    public void executeTestCase2() throws Exception {
        executeAndCompare("test_case_2");
    }

    @Test
    @DisplayName("ERROR_CODE_7 personFirstName is empty")
    public void executeTestCase3() throws Exception {
        executeAndCompare("test_case_3");
    }

    @Test
    @DisplayName("ERROR_CODE_8 personLastName is NULL")
    public void executeTestCase4() throws Exception {
        executeAndCompare("test_case_4");
    }

    @Test
    @DisplayName("ERROR_CODE_8 personLastName is empty")
    public void executeTestCase5() throws Exception {
        executeAndCompare("test_case_5");
    }

    @Test
    @DisplayName("ERROR_CODE_11 personBirthDate is NULL")
    public void executeTestCase6() throws Exception {
        executeAndCompare("test_case_6");
    }

    @Test
    @DisplayName("ERROR_CODE_12 personBirthDate in the future")
    public void executeTestCase7() throws Exception {
        executeAndCompare("test_case_7");
    }

    @Test
    @DisplayName("ERROR_CODE_16 personCode is NULL, must not be empty")
    public void executeTestCase23() throws Exception {
        executeAndCompare("test_case_23");
    }

    @Test
    @DisplayName("ERROR_CODE_16 personCode is empty, must not be empty")
    public void executeTestCase24() throws Exception {
        executeAndCompare("test_case_24");
    }

}
