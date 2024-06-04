package lv.javaguru.travel.insurance.rest.controller_v2_test.agreement_errors.medical_risk_limit_level;

import lv.javaguru.travel.insurance.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class V2AgreementMedicalRiskLimitLevelTest extends TravelCalculatePremiumControllerV2TestCase {

    //medicalRiskLimitLevelIc is not supported. Selected risk - TRAVEL_MEDICAL
    @Test
    public void execute23() throws Exception {
        executeAndCompare("not supported ic");
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "agreement_error/medical_risk_limit_level/";
    }
}
