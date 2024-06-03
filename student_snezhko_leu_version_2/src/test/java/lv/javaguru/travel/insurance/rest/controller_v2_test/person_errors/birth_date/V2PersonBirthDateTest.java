package lv.javaguru.travel.insurance.rest.controller_v2_test.person_errors.birth_date;

import lv.javaguru.travel.insurance.rest.controller_v2_test.TravelCalculatePremiumControllerV2TestCase;
import org.junit.jupiter.api.Test;

public class V2PersonBirthDateTest extends TravelCalculatePremiumControllerV2TestCase {

    //birthDate is null
    @Test
    public void execute6() throws Exception {
        executeAndCompare("test_case_6");
    }

    //birthDate is in the future
    @Test
    public void execute7() throws Exception {
        executeAndCompare("test_case_7");
    }
}
