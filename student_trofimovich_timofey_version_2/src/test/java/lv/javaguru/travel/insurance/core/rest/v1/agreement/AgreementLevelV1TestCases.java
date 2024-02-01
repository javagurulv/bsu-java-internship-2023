package lv.javaguru.travel.insurance.core.rest.v1.agreement;

import lv.javaguru.travel.insurance.core.rest.v1.TravelCalculatePremiumControllerV1TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementLevelV1TestCases extends TravelCalculatePremiumControllerV1TestCase {
    private static final String TEST_FILE_BASE_FOLDER = "agreement";
    @DisplayName("no error")
    @Test
    public void executeTestCase1() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/no error, all fields provided");
    }

    @DisplayName("ERROR_CODE_3=Field agreementDateFrom is empty!")
    @Test
    public void executeTestCase8() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_3=Field agreementDateFrom is empty!");
    }
    @DisplayName("ERROR_CODE_4=Field agreementDateTo is empty!")
    @Test
    public void executeTestCase9() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_4=Field agreementDateTo is empty!");
    }
    @DisplayName("ERROR_CODE_5=Field agreementDateFrom is in the past!")
    @Test
    public void executeTestCase10() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_5=Field agreementDateFrom is in the past!");
    }
    @DisplayName("ERROR_CODE_6=Field agreementDateTo is in the past!")
    @Test
    public void executeTestCase11() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_6=Field agreementDateTo is in the past!");
    }
    @DisplayName("ERROR_CODE_7=Field agreementDateFrom is not before field agreementDateTo!")
    @Test
    public void executeTestCase12() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_7=Field agreementDateFrom is not before field agreementDateTo!");
    }
    @DisplayName("multiple error codes")
    @Test
    public void executeTestCase22() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/all fields not provided, risk - travel medical");
    }
    @DisplayName("multiple error codes")
    @Test
    public void executeTestCase23() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/all fields not provided");
    }

}
