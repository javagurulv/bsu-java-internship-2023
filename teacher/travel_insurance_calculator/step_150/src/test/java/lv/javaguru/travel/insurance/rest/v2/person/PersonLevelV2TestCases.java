package lv.javaguru.travel.insurance.rest.v2.person;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonLevelV2TestCases extends TravelCalculatePremiumControllerV2TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "person";

    @Test
    @DisplayName("ERROR_CODE_7 one personFirstName is NULL, must not be empty")
    public void check_ERROR_CODE_7_NULL() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_7_personFirstName_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_7 one personFirstName is empty, must not be empty")
    public void check_ERROR_CODE_7_EMPTY() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_7_personFirstName_is_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_7 two personFirstName is empty, must not be empty")
    public void check_ERROR_CODE_7_two_persons_empty() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_7_two_personFirstName_is_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_7 two personFirstName is NULL, must not be empty")
    public void check_ERROR_CODE_7_two_persons_null() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_7_two_personFirstName_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_8 one personLastName is empty, must not be empty")
    public void check_ERROR_CODE_8_EMPTY() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_8_personLastName_is_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_8 one personLastName is NULL, must not be empty")
    public void check_ERROR_CODE_8_NULL() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_8_personLastName_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_8 two personLastName is NULL, must not be empty")
    public void check_ERROR_CODE_8_two_personLastName_NULL() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_8_two_personLastName_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_8 two personLastName is empty, must not be empty")
    public void check_ERROR_CODE_8_two_personLastName_EMPTY() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_8_two_personLastName_is_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_11 one personBirthDate is NULL, must not be empty")
    public void check_ERROR_CODE_11() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_11_personBirthDate_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_11 two personBirthDate is NULL, must not be empty")
    public void check_ERROR_CODE_11_two_persons() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_11_two_personBirthDate_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_12 one personBirthDate must be in the past")
    public void check_ERROR_CODE_12() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_12_personBirthDate_in_the_future");
    }

    @Test
    @DisplayName("ERROR_CODE_12 two personBirthDate must be in the past")
    public void check_ERROR_CODE_12_two_persons() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_12_two_personBirthDate_in_the_future");
    }

    @Test
    @DisplayName("ERROR_CODE_16 one person personCode is NULL, must not be empty")
    public void check_ERROR_CODE_16_NULL() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_16_person_code_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_16 one person personCode is empty, must not be empty")
    public void check_ERROR_CODE_16_EMPTY() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_16_person_code_is_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_16 two persons personCode is NULL, must not be empty")
    public void check_ERROR_CODE_16_two_persons_NULL() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_16_two_person_code_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_16 two persons personCode is empty, must not be empty")
    public void check_ERROR_CODE_16_two_persons_EMPTY() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_16_two_person_code_is_empty");
    }

}
