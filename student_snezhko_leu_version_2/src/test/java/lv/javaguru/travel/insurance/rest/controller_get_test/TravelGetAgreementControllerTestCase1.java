package lv.javaguru.travel.insurance.rest.controller_get_test;

import org.junit.jupiter.api.Test;

public class TravelGetAgreementControllerTestCase1 extends TravelGetAgreementControllerTest{
    @Override
    protected String getTestCaseName() {
        return "test_case_1";
    }

    @Override
    protected String getTestUuid() {
        return "11111111-1111-1111-1111-111111111111";
    }

    @Test
    public void execute() throws Exception{
        executeAndCompare();
    }
}
