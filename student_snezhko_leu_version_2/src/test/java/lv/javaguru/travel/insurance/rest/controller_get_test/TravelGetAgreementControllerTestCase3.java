package lv.javaguru.travel.insurance.rest.controller_get_test;

import org.junit.jupiter.api.Test;

public class TravelGetAgreementControllerTestCase3 extends TravelGetAgreementControllerTest{
    @Override
    protected String getTestCaseName() {
        return "test_case_3";
    }

    @Override
    protected String getTestUuid() {
        return "xxxxxxxxxx";
    }

    @Test
    public void execute() throws Exception{
        executeAndCompare();
    }
}
