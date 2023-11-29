package lv.javaguru.travel.insurance.rest.v1.person;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonLevelV1TestCases extends TravelCalculatePremiumControllerV1TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "person";

    @Test
    @DisplayName("ERROR_CODE_7 personFirstName is NULL")
    public void check_ERROR_CODE_7_NULL() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_7_personFirstName_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_7 personFirstName is empty")
    public void check_ERROR_CODE_7_EMPTY() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_7_personFirstName_is_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_8 personLastName is NULL")
    public void check_ERROR_CODE_8_NULL() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_8_personLastName_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_8 personLastName is empty")
    public void check_ERROR_CODE_8_EMPTY() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_8_personLastName_is_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_11 personBirthDate is NULL")
    public void check_ERROR_CODE_11() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_11_personBirthDate_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_12 personBirthDate in the future")
    public void check_ERROR_CODE_12() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_12_personBirthDate_in_the_future");
    }

    @Test
    @DisplayName("ERROR_CODE_16 personCode is NULL, must not be empty")
    public void check_ERROR_CODE_16_NULL() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_16_personCode_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_16 personCode is empty, must not be empty")
    public void check_ERROR_CODE_16_EMPTY() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_16_personCode_is_empty");
    }

}
