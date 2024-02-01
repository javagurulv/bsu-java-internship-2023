package lv.javaguru.travel.insurance.core.rest.v2.person;

import lv.javaguru.travel.insurance.core.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonLevelV2TestCases extends TravelCalculatePremiumControllerV2TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "person";
    @DisplayName("ERROR_CODE_16=Persons list is empty!")
    @Test
    public void executeTestCase16() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_16=Persons list is empty!");
    }
    @DisplayName("ERROR_CODE_1=Field personFirstName is empty!")
    @Test
    public void executeTestCase17() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_1=Field personFirstName is empty!");
    }
    @DisplayName("ERROR_CODE_1=Field personFirstName is null!")
    @Test
    public void executeTestCase18() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_1=Field personFirstName is null!");
    }
    @DisplayName("ERROR_CODE_2=Field personLastName is null!")
    @Test
    public void executeTestCase19() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_2=Field personLastName is null!");
    }
    @DisplayName("ERROR_CODE_2=Field personLastName is empty!")
    @Test
    public void executeTestCase20() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_2=Field personLastName is empty!");
    }
    @DisplayName("ERROR_CODE_12=Field dateOfBirth is empty!")
    @Test
    public void executeTestCase21() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_12=Field dateOfBirth is empty!");
    }
    @DisplayName("ERROR_CODE_13=Field dateOfBirth is from the future!")
    @Test
    public void executeTestCase22() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_13=Field dateOfBirth is from the future!");
    }
    @DisplayName("multiple errors, person fields are empty for one person")
    @Test
    public void executeTestCase23() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/multiple errors, person fields are empty for one person");
    }
    @DisplayName("multiple errors, person fields are empty for multiple persons")
    @Test
    public void executeTestCase24() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/multiple errors, person fields are empty for multiple persons");
    }
    @DisplayName("ERROR_CODE_17=Person uuid is empty!")
    @Test
    public void executeTestCase28() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_17=Person uuid is empty!");
    }
}
