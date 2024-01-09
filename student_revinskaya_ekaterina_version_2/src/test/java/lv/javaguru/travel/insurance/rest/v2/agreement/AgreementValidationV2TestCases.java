package lv.javaguru.travel.insurance.rest.v2.agreement;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementValidationV2TestCases extends TravelCalculatePremiumControllerV2Test {
    @Test
    @DisplayName("request with null agreement date from")
    public void testRequest2() throws Exception {
        equalsJsonFiles("agreements/test_case_2");
    }

    @Test
    @DisplayName("request with agreement date from in the past")
    public void testRequest3() throws Exception {
        equalsJsonFiles("agreements/test_case_3");
    }

    @Test
    @DisplayName("request with null agreement date to")
    public void testRequest4() throws Exception {
        equalsJsonFiles("agreements/test_case_4");
    }

    @Test
    @DisplayName("request with agreement date to in the past")
    public void testRequest5() throws Exception {
        equalsJsonFiles("agreements/test_case_5");
    }

    @Test
    @DisplayName("request with agreement date to less than agreement date from")
    public void testRequest6() throws Exception {
        equalsJsonFiles("agreements/test_case_6");
    }

    @Test
    @DisplayName("request with null country")
    public void testRequest7() throws Exception {
        equalsJsonFiles("agreements/test_case_7");
    }

    @Test
    @DisplayName("request with empty country")
    public void testRequest8() throws Exception {
        equalsJsonFiles("agreements/test_case_8");
    }

    @Test
    @DisplayName("request with country not supported by the system, RISK_TYPE - MEDICAL_RISK")
    public void testRequest9() throws Exception {
        equalsJsonFiles("agreements/test_case_9");
    }

    @Test
    @DisplayName("request with country not supported by the system, RISK_TYPE - TRAVEL_CANCELLATION selected")
    public void testRequest10() throws Exception {
        equalsJsonFiles("agreements/test_case_10");
    }

    @Test
    @DisplayName("request with null fields (one person)")
    public void testRequest24() throws Exception {
        equalsJsonFiles("agreements/test_case_24");
    }

    @Test
    @DisplayName("request with empty list of persons")
    public void testRequest25() throws Exception {
        equalsJsonFiles("agreements/test_case_25");
    }

    @Test
    @DisplayName("request with null list of persons")
    public void testRequest26() throws Exception {
        equalsJsonFiles("agreements/test_case_26");
    }
    @Test
    @DisplayName("request where travel cost null, RISK_TYPE-TRAVEL_CANCELLATION")
    public void testRequest28() throws Exception {
        equalsJsonFiles("agreements/test_case_28");
    }
    @Test
    @DisplayName("request where travel cost null, RISK_TYPE-TRAVEL_MEDICAL")
    public void testRequest29() throws Exception {
        equalsJsonFiles("agreements/test_case_29", true);
    }
}