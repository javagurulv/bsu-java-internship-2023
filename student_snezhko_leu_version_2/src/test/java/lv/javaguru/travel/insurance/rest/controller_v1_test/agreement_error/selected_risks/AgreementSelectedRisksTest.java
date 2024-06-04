package lv.javaguru.travel.insurance.rest.controller_v1_test.agreement_error.selected_risks;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementSelectedRisksTest extends TravelCalculatePremiumControllerTestCase {

    @DisplayName("Field selected_risks is null test")
    @Test
    public void execute13() throws Exception {
        executeAndCompare("test_case_13");
    }

    @DisplayName("Field selected_risks is empty test")
    @Test
    public void execute14() throws Exception {
        executeAndCompare("test_case_14");
    }

    @DisplayName("one of selected risks is not supported test")
    @Test
    public void execute15() throws Exception {
        executeAndCompare("test_case_15");
    }

    @DisplayName("two of selected risks is not supported test")
    @Test
    public void execute16() throws Exception {
        executeAndCompare("test_case_16");
    }

    @Override
    protected String getPathToTestCaseFolder() {
        return "agreement_error/selected_risks/";
    }
}
