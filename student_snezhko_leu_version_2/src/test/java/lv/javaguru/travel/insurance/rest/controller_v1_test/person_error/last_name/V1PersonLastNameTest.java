package lv.javaguru.travel.insurance.rest.controller_v1_test.person_error.last_name;

import lv.javaguru.travel.insurance.rest.controller_v1_test.TravelCalculatePremiumControllerTestCase;
import org.junit.jupiter.api.Test;

public class V1PersonLastNameTest extends TravelCalculatePremiumControllerTestCase {

    //last name is null
    @Test
    public void execute4() throws Exception {
        executeAndCompare("test_case_4");
    }

    //last name is empty
    @Test
    public void execute5() throws Exception {
        executeAndCompare("test_case_5");
    }
}
