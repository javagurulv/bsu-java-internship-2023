package lv.javaguru.travel.insurance.rest.v2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TrCalculatePremiumControllerTestCases extends TravelCalculatePremiumControllerTest {
    @Test
    @DisplayName("request without errors")
    public void testRequest1() throws Exception {
        equalsJsonFiles("test_case_1", true);
    }

    @Test
    @DisplayName("request with null agreement date from")
    public void testRequest2() throws Exception {
        equalsJsonFiles("test_case_2");
    }

    @Test
    @DisplayName("request with agreement date from in the past")
    public void testRequest3() throws Exception {
        equalsJsonFiles("test_case_3");
    }

    @Test
    @DisplayName("request with null agreement date to")
    public void testRequest4() throws Exception {
        equalsJsonFiles("test_case_4");
    }

    @Test
    @DisplayName("request with agreement date to in the past")
    public void testRequest5() throws Exception {
        equalsJsonFiles("test_case_5");
    }

    @Test
    @DisplayName("request with agreement date to less than agreement date from")
    public void testRequest6() throws Exception {
        equalsJsonFiles("test_case_6");
    }

    @Test
    @DisplayName("request with null country")
    public void testRequest7() throws Exception {
        equalsJsonFiles("test_case_7");
    }

    @Test
    @DisplayName("request with empty country")
    public void testRequest8() throws Exception {
        equalsJsonFiles("test_case_8");
    }

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
        equalsJsonFiles("test_case_11", true);
    }

    @Test
    @DisplayName("request with empty selected risks")
    public void testRequest12() throws Exception {
        equalsJsonFiles("test_case_12");
    }

    @Test
    @DisplayName("request with null selected risks")
    public void testRequest13() throws Exception {
        equalsJsonFiles("test_case_13");
    }

    @Test
    @DisplayName("request with not exist selected risks")
    public void testRequest14() throws Exception {
        equalsJsonFiles("test_case_14");
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