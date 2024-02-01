package lv.javaguru.travel.insurance.core.rest.v1.risk_travel_medical;

import lv.javaguru.travel.insurance.core.rest.v1.TravelCalculatePremiumControllerV1TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TravelMedicalRiskV1TestCases extends TravelCalculatePremiumControllerV1TestCase {
    private static final String TEST_FILE_BASE_FOLDER = "risk_travel_medical";
    @DisplayName("ERROR_CODE_10=Field country is empty!")
    @Test
    public void executeTestCase17() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_10=Field country is null!");
    }
    @DisplayName("ERROR_CODE_10=Field country is empty!")
    @Test
    public void executeTestCase18() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_10=Field country is empty!");
    }
    @DisplayName("ERROR_CODE_11=Country {country} is not supported by the system!")
    @Test
    public void executeTestCase19() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_11=Country {country} is not supported by the system!");
    }
    @DisplayName("Field medicalRiskLimit is empty!")
    @Test
    public void executeTestCase20() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/Field medicalRiskLimit is empty!");
    }
    @DisplayName("Medical risk limit {value} is not supported by the system!")
    @Test
    public void executeTestCase21() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/Medical risk limit {value} is not supported by the system!");
    }
    @DisplayName("ERROR_CODE_19=Field travelCost is empty!")
    @Test
    public void executeTestCase26() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_19=Field travelCost is empty!");
    }
}
