package lv.javaguru.travel.insurance.rest.controller_get_test;

import org.junit.jupiter.api.Test;

public class TravelGetAgreementControllerTestCase2 extends TravelGetAgreementControllerTest{
    @Override
    protected String getTestCaseName() {
        return "test_case_2";
    }

    @Override
    protected String getTestUuid() {
        return "99999999-9999-9999-9999-999999999999";
    }

    @Test
    public void execute() throws Exception{
        executeAndCompare();
    }
}
