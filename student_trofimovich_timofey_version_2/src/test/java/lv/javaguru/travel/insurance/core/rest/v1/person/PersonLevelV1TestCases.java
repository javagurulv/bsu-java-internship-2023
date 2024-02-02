package lv.javaguru.travel.insurance.core.rest.v1.person;

import lv.javaguru.travel.insurance.core.rest.v1.TravelCalculatePremiumControllerV1TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonLevelV1TestCases extends TravelCalculatePremiumControllerV1TestCase {
    private static final String TEST_FILE_BASE_FOLDER = "person";
    @DisplayName("ERROR_CODE_1=Field personFirstName is empty!")
    @Test
    public void executeTestCase2() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_1=Field personFirstName is null!");
    }
    @DisplayName("ERROR_CODE_1=Field personFirstName is empty!")
    @Test
    public void executeTestCase3() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_1=Field personFirstName is empty!");
    }
    @DisplayName("ERROR_CODE_2=Field personLastName is empty!")
    @Test
    public void executeTestCase4() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_2=Field personLastName is null!");
    }
    @DisplayName("ERROR_CODE_2=Field personLastName is empty!")
    @Test
    public void executeTestCase5() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_2=Field personLastName is empty!");
    }
    @DisplayName("ERROR_CODE_12=Field dateOfBirth is empty!")
    @Test
    public void executeTestCase6() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_12=Field dateOfBirth is empty!");
    }
    @DisplayName("ERROR_CODE_13=Field dateOfBirth is from the future!")
    @Test
    public void executeTestCase7() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_13=Field dateOfBirth is from the future!");
    }
    @DisplayName("ERROR_CODE_17=Person uuid is empty!")
    @Test
    public void executeTestCase24() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_17=Person uuid is empty!");
    }
}
