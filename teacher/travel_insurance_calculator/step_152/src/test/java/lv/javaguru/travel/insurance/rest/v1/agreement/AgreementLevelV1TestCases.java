package lv.javaguru.travel.insurance.rest.v1.agreement;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementLevelV1TestCases extends TravelCalculatePremiumControllerV1TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "agreement";

    @Test
    @DisplayName("ERROR_CODE_2 agreementDateFrom is NULL")
    public void check_ERROR_CODE_2() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_2_agreementDateFrom_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_4 agreementDateTo is NULL")
    public void check_ERROR_CODE_4() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_4_agreementDateTo_is_null");
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
    @DisplayName("ERROR_CODE_10 country is empty, must not be empty")
    public void check_ERROR_CODE_10_EMPTY() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_10_country_is_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_10 country is NULL, must not be empty")
    public void check_ERROR_CODE_10_NULL() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_10_country_is_null");
    }

    @Test
    @DisplayName("Multiple errors all field is NULL except selected_risks")
    public void check_MultipleErrors_allFieldsNull_except_selectedRisks() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/Multiple_errors_all_all_fields_NULL_except_selectedRisks");
    }

    @Test
    @DisplayName("Multiple errors all field is NULL")
    public void check_Multiple_errors_all_fields_NULL() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/Multiple_errors_all_fields_NULL");
    }

}
