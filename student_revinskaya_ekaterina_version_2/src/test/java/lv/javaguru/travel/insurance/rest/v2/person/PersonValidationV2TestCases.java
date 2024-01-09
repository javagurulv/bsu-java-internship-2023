package lv.javaguru.travel.insurance.rest.v2.person;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonValidationV2TestCases extends TravelCalculatePremiumControllerV2Test {

    @Test
    @DisplayName("request with empty first name of first person")
    public void testRequest1() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_1_empty_first_name");
    }

    @Test
    @DisplayName("request with null first name of second person")
    public void testRequest2() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_1_null_first_name");
    }

    @Test
    @DisplayName("request with null last name of first person")
    public void testRequest3() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_2_null_last_name");
    }

    @Test
    @DisplayName("request with empty last name of first person")
    public void testRequest4() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_2_empty_last_name");
    }

    @Test
    @DisplayName("request with null birthday of both persons")
    public void testRequest5() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_12_null_birthday");
    }

    @Test
    @DisplayName("request with birthday of second person in future")
    public void testRequest6() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_13_birthday_in_future_person_2");
    }

    @Test
    @DisplayName("request with birthday of first person in future")
    public void testRequest7() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_13_birthday_in_future_person_1");
    }

    @Test
    @DisplayName("request with null personal code of one person")
    public void testRequest8() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_17_null_personal_code");
    }

    @Test
    @DisplayName("request with not exist medical risk limit level of both persons")
    public void testRequest9() throws Exception {
        equalsJsonFiles("person/ERROR_CODE_15_medical_risk_limit_level_not_exist");
    }

    @Test
    @DisplayName("request with null fields for the first person")
    public void testRequest10() throws Exception {
        equalsJsonFiles("person/all_fields_null_for_one_person");
    }

    @Test
    @DisplayName("request with null fields for both persons")
    public void testRequest11() throws Exception {
        equalsJsonFiles("person/all_fields_null_for_both_persons");
    }

}