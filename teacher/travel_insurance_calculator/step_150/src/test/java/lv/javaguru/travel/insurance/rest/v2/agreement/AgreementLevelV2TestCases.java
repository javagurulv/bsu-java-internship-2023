package lv.javaguru.travel.insurance.rest.v2.agreement;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementLevelV2TestCases extends TravelCalculatePremiumControllerV2TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "agreement";

    @Test
    @DisplayName("ERROR_CODE_2 agreementDateFrom must not be empty")
    public void check_ERROR_CODE_2() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_2_agreementDateFrom_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_4 agreementDateTo must not be empty")
    public void check_ERROR_CODE_4() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_2_agreementDateTo_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_1 agreementDateFrom must be in the future")
    public void check_ERROR_CODE_1() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_1_agreementDateFrom_must_be_in_the_future");
    }

    @Test
    @DisplayName("ERROR_CODE_3 agreementDateTo must be in the future")
    public void check_ERROR_CODE_3() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_3_agreementDateTo_must_be_in_the_future");
    }

    @Test
    @DisplayName("ERROR_CODE_5 agreementDateFrom must be less than agreementDateTo")
    public void check_ERROR_CODE_5() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_5_agreementDateFrom_must_be_less_then_agreementDateTo");
    }

    @Test
    @DisplayName("ERROR_CODE_10 country is NULL, must not be empty")
    public void check_ERROR_CODE_10_NULL() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_10_country_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_10 country is empty, must not be empty")
    public void check_ERROR_CODE_10_EMPTY() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_10_country_is_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_15 country not supported")
    public void check_ERROR_CODE_15() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_15_country_not_supported");
    }

}
