package lv.javaguru.travel.insurance.rest.v1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TrCalculatePremiumControllerTestCases extends TravelCalculatePremiumControllerTest {
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
    @DisplayName("request with null agreement date from")
    public void testRequest5() throws Exception {
        equalsJsonFiles("test_case_5");
    }

    @Test
    @DisplayName("request with agreement date from in the past")
    public void testRequest6() throws Exception {
        equalsJsonFiles("test_case_6");
    }

    @Test
    @DisplayName("request with null agreement date to")
    public void testRequest7() throws Exception {
        equalsJsonFiles("test_case_7");
    }

    @Test
    @DisplayName("request with agreement date to in the past")
    public void testRequest8() throws Exception {
        equalsJsonFiles("test_case_8");
    }
    @Test
    @DisplayName("request with agreement date to less than agreement date from")
    public void testRequest9() throws Exception {
        equalsJsonFiles("test_case_9");
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
    @DisplayName("request with null country, RISK_TYPE-TRAVEL_MEDICAL")
    public void testRequest12() throws Exception {
        equalsJsonFiles("test_case_12");
    }

    @Test
    @DisplayName("request with empty country, RISK_TYPE-TRAVEL_MEDICAL")
    public void testRequest13() throws Exception {
        equalsJsonFiles("test_case_13");
    }

    @Test
    @DisplayName("request with empty country, RISK_TYPE-TRAVEL_EVACUATION")
    public void testRequest14() throws Exception {
        equalsJsonFiles("test_case_14");
    }

    @Test
    @DisplayName("request with country not supported by the system")
    public void testRequest15() throws Exception {
        equalsJsonFiles("test_case_15");
    }

    @Test
    @DisplayName("request with null selected_risks")
    public void testRequest16() throws Exception {
        equalsJsonFiles("test_case_16");
    }

    @Test
    @DisplayName("request with empty selected_risks")
    public void testRequest17() throws Exception {
        equalsJsonFiles("test_case_17");
    }

    @Test
    @DisplayName("request with selectedRisk is not supported by the system")
    public void testRequest18() throws Exception {
        equalsJsonFiles("test_case_18");
    }
    @Test
    @DisplayName("request with selectedRisks is not supported by the system(two risks)")
    public void testRequest19() throws Exception {
        equalsJsonFiles("test_case_19");
    }
    @Test
    @DisplayName("request where all fields are null")
    public void testRequest20() throws Exception {
        equalsJsonFiles("test_case_20");
    }
    @Test
    @DisplayName("request without errors")
    public void testRequest21() throws Exception {
        equalsJsonFiles("test_case_21", true);
    }
    @Test
    @DisplayName("request with null personal code")
    public void testRequest22() throws Exception {
        equalsJsonFiles("test_case_22");
    }

}
