package lv.javaguru.travel.insurance.rest.controller_v1_test.agreement_error.selected_risks;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementSelectedRisksTest extends TravelCalculatePremiumControllerTestCase {

    @DisplayName("Field selected_risks is null test")
    @Test
    public void execute13() throws Exception {
        executeAndCompare("selected_risks is null");
    }

    @DisplayName("Field selected_risks is empty test")
    @Test
    public void execute14() throws Exception {
        executeAndCompare("selected_risks is empty");
    }

    @DisplayName("one of selected risks is not supported test")
    @Test
    public void execute15() throws Exception {
        executeAndCompare("one not supported selected risk");
    }

    @DisplayName("two of selected risks is not supported test")
    @Test
    public void execute16() throws Exception {
        executeAndCompare("two not supported selected risks");
    }

    @Override
    protected String getPathToTestCaseFolder() {
        return "agreement_error/selected_risks/";
    }
}
