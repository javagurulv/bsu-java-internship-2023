package lv.javaguru.travel.insurance.rest.v2.risk_travel_medical;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelMedicalRiskV2TestCases extends TravelCalculatePremiumControllerV2TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "risk_travel_medical";

    @Test
    @DisplayName("ERROR_CODE_13 one person medicalRiskLimitLevel is NULL [TRAVEL_MEDICAL], must not be empty")
    public void check_ERROR_CODE_13_NULL() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_13_medicalRiskLimitLevel_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_13 one person medicalRiskLimitLevel is empty [TRAVEL_MEDICAL], must not be empty")
    public void check_ERROR_CODE_13_EMPTY() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_13_medicalRiskLimitLevel_is_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_13 two persons medicalRiskLimitLevel is NULL [TRAVEL_MEDICAL], must not be empty")
    public void check_ERROR_CODE_13_two_medicalRiskLimitLevel_NULL() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_13_two_medicalRiskLimitLevel_is_null");
    }

    @Test
    @DisplayName("ERROR_CODE_13 two persons medicalRiskLimitLevel is empty [TRAVEL_MEDICAL], must not be empty")
    public void check_ERROR_CODE_13_two_medicalRiskLimitLevel_EMPTY() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_13_two_medicalRiskLimitLevel_is_empty");
    }

    @Test
    @DisplayName("ERROR_CODE_14 one person medicalRiskLimitLevel is not supported [TRAVEL_MEDICAL]")
    public void check_ERROR_CODE_14() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_14_medicalRiskLimitLevel_is_not_supported");
    }

    @Test
    @DisplayName("ERROR_CODE_14 two persons medicalRiskLimitLevel is not supported [TRAVEL_MEDICAL]")
    public void check_ERROR_CODE_14_two() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_14_two_medicalRiskLimitLevel_is_not_supported");
    }

}
