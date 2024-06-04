package lv.javaguru.travel.insurance.rest.controller_v2_test.person_errors.last_name;

import lv.javaguru.travel.insurance.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class V2PersonLastNameTest extends TravelCalculatePremiumControllerV2TestCase {

    //lastName is null
    @Test
    public void execute4() throws Exception {
        executeAndCompare("last_name is null");
    }

    //lastName is empty
    @Test
    public void execute5() throws Exception {
        executeAndCompare("last_name is empty");
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "person_errors/last_name/";
    }
}
