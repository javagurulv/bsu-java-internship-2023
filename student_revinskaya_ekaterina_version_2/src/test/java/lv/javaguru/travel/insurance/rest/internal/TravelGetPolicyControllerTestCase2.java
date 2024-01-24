package lv.javaguru.travel.insurance.rest.internal;

import org.junit.jupiter.api.DisplayName;

public class TravelGetPolicyControllerTestCase2 extends TravelGetPolicyControllerTest {
    @Override
    @DisplayName("request with uuid in wrong pattern")
    protected String getTestCaseName() {
        return "uuid_wrong_pattern";
    }

    @Override
    protected boolean testWithCorrectUuid() {
        return false;
    }

    @Override
    protected String providedUuid() {
        return "1435-i6yi-13455-345jk1h5k4";
    }
}
