package lv.javaguru.travel.insurance.rest.internal;

import org.junit.jupiter.api.DisplayName;

public class TravelGetPolicyControllerTestCase3 extends TravelGetPolicyControllerTest {
    @Override
    @DisplayName("request with not exist uuid")
    protected String getTestCaseName() {
        return "not_exist_uuid";
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
