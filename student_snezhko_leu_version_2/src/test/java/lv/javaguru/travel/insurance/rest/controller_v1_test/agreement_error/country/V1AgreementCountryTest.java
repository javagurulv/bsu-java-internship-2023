package lv.javaguru.travel.insurance.rest.controller_v1_test.agreement_error.country;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class V1AgreementCountryTest extends TravelCalculatePremiumControllerTestCase {

    @Override
    protected String getPathToTestCaseFolder() {
        return "agreement_error/country/";
    }

    @DisplayName("Country is empty. TM")
    @Test
    public void executeTest17() throws Exception {
        executeAndCompare("test_case_17");
    }

    @DisplayName("Country is null. TM")
    @Test
    public void executeTest18() throws Exception {
        executeAndCompare("test_case_18");
    }

    @DisplayName("Country is null. TE")
    @Test
    public void executeTest21() throws Exception {
        executeAndCompare("test_case_21");
    }

    @DisplayName("Country is empty. TE")
    @Test
    public void executeTest22() throws Exception {
        executeAndCompare("test_case_22");
    }
}
