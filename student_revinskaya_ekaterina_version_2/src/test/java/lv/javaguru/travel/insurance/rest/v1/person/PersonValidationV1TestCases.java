package lv.javaguru.travel.insurance.rest.v1.person;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonValidationV1TestCases extends TravelCalculatePremiumControllerV1Test {

    @Test
    @DisplayName("request with null first name")
    public void testRequest1() throws Exception {
        equalsJsonFiles("test_case_1");
    }

    @Test
    @DisplayName("request with empty first name")
    public void testRequest2() throws Exception {
        equalsJsonFiles("test_case_2");
    }

    @Test
    @DisplayName("request with null last name")
    public void testRequest3() throws Exception {
        equalsJsonFiles("test_case_3");
    }

    @Test
    @DisplayName("request with empty last name")
    public void testRequest4() throws Exception {
        equalsJsonFiles("test_case_4");
    }

    @Test
    @DisplayName("request with null personBirthDate")
    public void testRequest10() throws Exception {
        equalsJsonFiles("test_case_10");
    }

    @Test
    @DisplayName("request with personBirthDate in the future")
    public void testRequest11() throws Exception {
        equalsJsonFiles("test_case_11");
    }

    @Test
    @DisplayName("request with null personal code")
    public void testRequest22() throws Exception {
        equalsJsonFiles("test_case_22");
    }

}
