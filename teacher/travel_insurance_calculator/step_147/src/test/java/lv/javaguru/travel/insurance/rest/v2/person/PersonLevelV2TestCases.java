package lv.javaguru.travel.insurance.rest.v2.person;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonLevelV2TestCases extends TravelCalculatePremiumControllerV2TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "person";

    @Test
    @DisplayName("ERROR_CODE_7 one personFirstName is NULL, must not be empty")
    public void executeTestCase2() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_2");
    }

    @Test
    @DisplayName("ERROR_CODE_7 one personFirstName is empty, must not be empty")
    public void executeTestCase3() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_3");
    }

    @Test
    @DisplayName("ERROR_CODE_7 two personFirstName is empty, must not be empty")
    public void executeTestCase4() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_4");
    }

    @Test
    @DisplayName("ERROR_CODE_7 two personFirstName is NULL, must not be empty")
    public void executeTestCase5() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_5");
    }

    @Test
    @DisplayName("ERROR_CODE_8 one personLastName is empty, must not be empty")
    public void executeTestCase6() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_6");
    }

    @Test
    @DisplayName("ERROR_CODE_8 one personLastName is NULL, must not be empty")
    public void executeTestCase7() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_7");
    }

    @Test
    @DisplayName("ERROR_CODE_8 two personLastName is NULL, must not be empty")
    public void executeTestCase8() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_8");
    }

    @Test
    @DisplayName("ERROR_CODE_8 two personLastName is empty, must not be empty")
    public void executeTestCase9() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_9");
    }

    @Test
    @DisplayName("ERROR_CODE_11 one personBirthDate is NULL, must not be empty")
    public void executeTestCase10() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_10");
    }

    @Test
    @DisplayName("ERROR_CODE_11 two personBirthDate is NULL, must not be empty")
    public void executeTestCase11() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_11");
    }

    @Test
    @DisplayName("ERROR_CODE_12 one personBirthDate must be in the past")
    public void executeTestCase12() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_12");
    }

    @Test
    @DisplayName("ERROR_CODE_12 two personBirthDate must be in the past")
    public void executeTestCase13() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_13");
    }

    @Test
    @DisplayName("ERROR_CODE_16 one person personCode is NULL, must not be empty")
    public void executeTestCase32() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_32");
    }

    @Test
    @DisplayName("ERROR_CODE_16 one person personCode is empty, must not be empty")
    public void executeTestCase33() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_33");
    }

    @Test
    @DisplayName("ERROR_CODE_16 two persons personCode is NULL, must not be empty")
    public void executeTestCase34() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_34");
    }

    @Test
    @DisplayName("ERROR_CODE_16 two persons personCode is empty, must not be empty")
    public void executeTestCase35() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/test_case_35");
    }

}
