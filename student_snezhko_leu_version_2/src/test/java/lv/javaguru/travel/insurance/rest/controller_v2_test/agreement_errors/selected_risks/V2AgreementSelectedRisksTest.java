package lv.javaguru.travel.insurance.rest.controller_v2_test.agreement_errors.selected_risks;

import lv.javaguru.travel.insurance.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class V2AgreementSelectedRisksTest extends TravelCalculatePremiumControllerV2TestCase {

    //Field selectedRisks is null
    @Test
    public void execute13() throws Exception {
        executeAndCompare("test_case_13");
    }

    //Field selectedRisks is empty
    @Test
    public void execute14() throws Exception {
        executeAndCompare("test_case_14");
    }

    //one risk is not supported
    @Test
    public void execute15() throws Exception {
        executeAndCompare("test_case_15");
    }

    //two risks are not supported
    @Test
    public void execute16() throws Exception {
        executeAndCompare("test_case_16");
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "agreement_error/selected_risks/";
    }
}
