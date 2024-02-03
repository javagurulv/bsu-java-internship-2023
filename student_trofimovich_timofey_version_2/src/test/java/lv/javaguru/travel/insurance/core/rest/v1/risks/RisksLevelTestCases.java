package lv.javaguru.travel.insurance.core.rest.v1.risks;

import lv.javaguru.travel.insurance.core.rest.v1.TravelCalculatePremiumControllerV1TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RisksLevelTestCases extends TravelCalculatePremiumControllerV1TestCase {
    private static final String TEST_FILE_BASE_FOLDER = "risks";
    @DisplayName("ERROR_CODE_8 = Field selectedRisks is empty!")
    @Test
    public void executeTestCase13() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_8 = Field selectedRisks is null!");
    }
    @DisplayName("ERROR_CODE_8 = Field selectedRisks is empty!")
    @Test
    public void executeTestCase14() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_8 = Field selectedRisks is empty!");
    }

    @DisplayName("ERROR_CODE_9=Selected risk {name} is not supported by the system!")
    @Test
    public void executeTestCase15() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_9=Selected risk {name} is not supported by the system! (one risk)");
    }
    @DisplayName("ERROR_CODE_9=Selected risk {name} is not supported by the system! x2")
    @Test
    public void executeTestCase16() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_9=Selected risk {name} is not supported by the system! (two risks)");
    }
}
