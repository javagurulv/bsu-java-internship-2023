package lv.javaguru.travel.insurance.core.rest.v2.agreement;

import lv.javaguru.travel.insurance.core.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementLevelV2TestCases extends TravelCalculatePremiumControllerV2TestCase {
    private static final String TEST_FILE_BASE_FOLDER = "agreement";
    @DisplayName("no error, all fields provided")
    @Test
    public void executeTestCase1() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/no error, all fields provided");
    }
    @DisplayName("ERROR_CODE_3=Field agreementDateFrom is empty!")
    @Test
    public void executeTestCase2() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_3=Field agreementDateFrom is empty!");
    }
    @DisplayName("ERROR_CODE_4=Field agreementDateTo is empty!")
    @Test
    public void executeTestCase3() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_4=Field agreementDateTo is empty!");
    }
    @DisplayName("ERROR_CODE_5=Field agreementDateFrom is in the past!")
    @Test
    public void executeTestCase4() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_5=Field agreementDateFrom is in the past!");
    }
    @DisplayName("ERROR_CODE_6=Field agreementDateTo is in the past!")
    @Test
    public void executeTestCase5() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_6=Field agreementDateTo is in the past!");
    }
    @DisplayName("ERROR_CODE_7=Field agreementDateFrom is not before field agreementDateTo!")
    @Test
    public void executeTestCase6() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_7=Field agreementDateFrom is not before field agreementDateTo!");
    }

    @DisplayName("multiple errors, all fields not provided, RISK_TYPE - TRAVEL_MEDICAL")
    @Test
    public void executeTestCase25() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/multiple errors, all fields not provided, RISK_TYPE - TRAVEL_MEDICAL");
    }
    @DisplayName("multiple errors, all fields not provided")
    @Test
    public void executeTestCase26() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/multiple errors, all fields not provided");
    }
}
