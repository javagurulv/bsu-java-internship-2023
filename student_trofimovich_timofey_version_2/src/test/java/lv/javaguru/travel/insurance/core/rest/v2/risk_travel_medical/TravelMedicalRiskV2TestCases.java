package lv.javaguru.travel.insurance.core.rest.v2.risk_travel_medical;

import lv.javaguru.travel.insurance.core.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelMedicalRiskV2TestCases extends TravelCalculatePremiumControllerV2TestCase {
    private static final String TEST_FILE_BASE_FOLDER = "risk_travel_medical";
    @DisplayName("ERROR_CODE_10=Field country is empty!")
    @Test
    public void executeTestCase11() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_10=Field country is empty!");
    }
    @DisplayName("ERROR_CODE_10=Field country is null!")
    @Test
    public void executeTestCase12() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_10=Field country is null!");
    }
    @DisplayName("ERROR_CODE_11=Country {country} is not supported by the system!")
    @Test
    public void executeTestCase13() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_11=Country {country} is not supported by the system!");
    }
    @DisplayName("ERROR_CODE_14=Field medicalRiskLimit is empty!")
    @Test
    public void executeTestCase14() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_14=Field medicalRiskLimit is empty!");
    }
    @DisplayName("ERROR_CODE_15=Medical risk limit {value} is not supported by the system!")
    @Test
    public void executeTestCase15() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_15=Medical risk limit {value} is not supported by the system!");
    }
    @DisplayName("no error, travel cost is empty, risk is TRAVEL_MEDICAL")
    @Test
    public void executeTestCase30() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/no error, travel cost is empty, risk is TRAVEL_MEDICAL");
    }
}
