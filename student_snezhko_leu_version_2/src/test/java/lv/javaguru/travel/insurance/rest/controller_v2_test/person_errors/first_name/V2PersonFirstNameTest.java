package lv.javaguru.travel.insurance.rest.controller_v2_test.person_errors.first_name;

import lv.javaguru.travel.insurance.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class V2PersonFirstNameTest extends TravelCalculatePremiumControllerV2TestCase {

    //firstName is empty
    @Test
    public void execute3() throws Exception {
        executeAndCompare("first_name is empty");
    }

    //firstName is null
    @Test
    public void execute2() throws Exception {
        executeAndCompare("first_name is null");
    }

    @Override
    protected String getTestCaseFolderPath() {
        return "person_errors/first_name/";
    }
}
