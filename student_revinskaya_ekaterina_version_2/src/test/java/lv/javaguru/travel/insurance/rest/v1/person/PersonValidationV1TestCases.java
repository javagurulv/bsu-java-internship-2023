package lv.javaguru.travel.insurance.rest.v1.person;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonValidationV1TestCases extends TravelCalculatePremiumControllerV1Test {

    @Test
    @DisplayName("request with null first name")
    public void testRequest1() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_1_null_first_name");
    }

    @Test
    @DisplayName("request with empty first name")
    public void testRequest2() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_1_empty_first_name");
    }

    @Test
    @DisplayName("request with null last name")
    public void testRequest3() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_2_null_last_name");
    }

    @Test
    @DisplayName("request with empty last name")
    public void testRequest4() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_2_empty_last_name");
    }

    @Test
    @DisplayName("request with null personBirthDate")
    public void testRequest5() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_12_null_person_birthday");
    }

    @Test
    @DisplayName("request with personBirthDate in the future")
    public void testRequest6() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_13_person_birthday_in_future");
    }

    @Test
    @DisplayName("request with null personal code")
    public void testRequest7() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_17_null_personal_code");
    }

}
