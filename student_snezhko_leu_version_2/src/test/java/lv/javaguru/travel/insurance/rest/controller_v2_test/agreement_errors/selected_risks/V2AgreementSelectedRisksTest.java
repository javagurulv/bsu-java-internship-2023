package lv.javaguru.travel.insurance.rest.controller_v2_test.agreement_errors.selected_risks;

import lv.javaguru.travel.insurance.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class V2AgreementSelectedRisksTest extends TravelCalculatePremiumControllerV2TestCase {

    //Field selectedRisks is null
    @Test
    public void execute13() throws Exception {
        executeAndCompare("selected_risks is null");
    }

    //Field selectedRisks is empty
    @Test
    public void execute14() throws Exception {
        executeAndCompare("selected_risks is empty");
    }

    //one risk is not supported
    @Test
    public void execute15() throws Exception {
        executeAndCompare("one not supported risk");
    }

    //two risks are not supported
    @Test
    public void execute16() throws Exception {
        executeAndCompare("two not supported risks");
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "agreement_error/selected_risks/";
    }
}
