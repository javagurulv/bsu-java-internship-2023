package lv.javaguru.travel.insurance.core.rest.v2.risks;

import lv.javaguru.travel.insurance.core.rest.v2.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RisksLevelTestCases extends TravelCalculatePremiumControllerV2TestCase {

    private static final String TEST_FILE_BASE_FOLDER = "risks";
    @DisplayName("ERROR_CODE_8=Field selectedRisks is null!")
    @Test
    public void executeTestCase7() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_8=Field selectedRisks is null!");
    }
    @DisplayName("ERROR_CODE_8=Field selectedRisks is empty!")
    @Test
    public void executeTestCase8() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_8=Field selectedRisks is empty!");
    }
    @DisplayName("ERROR_CODE_9=Selected risk {name} is not supported by the system! (one risk)")
    @Test
    public void executeTestCase9() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_9=Selected risk {name} is not supported by the system! (one risk)");
    }
    @DisplayName("ERROR_CODE_9=Selected risk {name} is not supported by the system! (two risks)")
    @Test
    public void executeTestCase10() throws Exception {
        executeAndCompare(TEST_FILE_BASE_FOLDER + "/ERROR_CODE_9=Selected risk {name} is not supported by the system! (two risks)");
    }
}
