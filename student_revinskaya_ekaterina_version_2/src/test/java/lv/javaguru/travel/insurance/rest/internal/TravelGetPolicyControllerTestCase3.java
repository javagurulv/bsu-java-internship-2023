package lv.javaguru.travel.insurance.rest.internal;

public class TravelGetPolicyControllerTestCase3 extends TravelGetPolicyControllerTest {
    @Override
    protected String getTestCaseName() {
        return "test_case_3";
    }

    @Override
    protected boolean testWithCorrectUuid() {
        return false;
    }

    @Override
    protected String providedUuid() {
        return "11bef5e1-d079-4421-9e3c-3329b1e3c836";
    }
}
