package lv.javaguru.travel.insurance.rest.v2.agreement;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonValidationV2TestCases extends TravelCalculatePremiumControllerV2Test {


    @Test
    @DisplayName("request with country not supported by the system, RISK_TYPE - MEDICAL_RISK")
    public void testRequest9() throws Exception {
        equalsJsonFiles("test_case_9");
    }

    @Test
    @DisplayName("request with country not supported by the system, RISK_TYPE - TRAVEL_CANCELLATION selected")
    public void testRequest10() throws Exception {
        equalsJsonFiles("test_case_10");
    }

    @Test
    @DisplayName("request with not exist medical risk limit level of both persons")
    public void testRequest11() throws Exception {
        equalsJsonFiles("test_case_11");
    }

    @Test
    @DisplayName("request with empty first name of first person")
    public void testRequest15() throws Exception {
        equalsJsonFiles("test_case_15");
    }

    @Test
    @DisplayName("request with null first name of second person")
    public void testRequest16() throws Exception {
        equalsJsonFiles("test_case_16");
    }

    @Test
    @DisplayName("request with null last name of first person")
    public void testRequest17() throws Exception {
        equalsJsonFiles("test_case_17");
    }

    @Test
    @DisplayName("request with empty last name of first person")
    public void testRequest18() throws Exception {
        equalsJsonFiles("test_case_18");
    }

    @Test
    @DisplayName("request with null birthday of both persons")
    public void testRequest19() throws Exception {
        equalsJsonFiles("test_case_19");
    }

    @Test
    @DisplayName("request with birthday of second person in future")
    public void testRequest20() throws Exception {
        equalsJsonFiles("test_case_20");
    }

    @Test
    @DisplayName("request with birthday of first person in future")
    public void testRequest21() throws Exception {
        equalsJsonFiles("test_case_21");
    }

    @Test
    @DisplayName("request with null fields for the first person")
    public void testRequest22() throws Exception {
        equalsJsonFiles("test_case_22");
    }

    @Test
    @DisplayName("request with null fields for both persons")
    public void testRequest23() throws Exception {
        equalsJsonFiles("test_case_23");
    }

    @Test
    @DisplayName("request with null fields (one person)")
    public void testRequest24() throws Exception {
        equalsJsonFiles("test_case_24");
    }

    @Test
    @DisplayName("request with empty list of persons")
    public void testRequest25() throws Exception {
        equalsJsonFiles("test_case_25");
    }

    @Test
    @DisplayName("request with null list of persons")
    public void testRequest26() throws Exception {
        equalsJsonFiles("test_case_26");
    }

    @Test
    @DisplayName("request with null personal code of one person")
    public void testRequest27() throws Exception {
        equalsJsonFiles("test_case_27");
    }

}