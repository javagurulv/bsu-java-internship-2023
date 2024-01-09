package lv.javaguru.travel.insurance.rest.v1.agreements;

import lv.javaguru.travel.insurance.rest.v1.TravelCalculatePremiumControllerV1Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementValidationV1TestCases extends TravelCalculatePremiumControllerV1Test {

    @Test
    @DisplayName("request with null agreement date from")
    public void testRequest5() throws Exception {
        equalsJsonFiles("agreements/test_case_5");
    }

    @Test
    @DisplayName("request with agreement date from in the past")
    public void testRequest6() throws Exception {
        equalsJsonFiles("agreements/test_case_6");
    }

    @Test
    @DisplayName("request with null agreement date to")
    public void testRequest7() throws Exception {
        equalsJsonFiles("agreements/test_case_7");
    }

    @Test
    @DisplayName("request with agreement date to in the past")
    public void testRequest8() throws Exception {
        equalsJsonFiles("agreements/test_case_8");
    }
    @Test
    @DisplayName("request with agreement date to less than agreement date from")
    public void testRequest9() throws Exception {
        equalsJsonFiles("agreements/test_case_9");
    }

    @Test
    @DisplayName("request with null country, RISK_TYPE-TRAVEL_MEDICAL")
    public void testRequest12() throws Exception {
        equalsJsonFiles("agreements/test_case_12");
    }

    @Test
    @DisplayName("request with empty country, RISK_TYPE-TRAVEL_MEDICAL")
    public void testRequest13() throws Exception {
        equalsJsonFiles("agreements/test_case_13");
    }

    @Test
    @DisplayName("request with empty country, RISK_TYPE-TRAVEL_EVACUATION")
    public void testRequest14() throws Exception {
        equalsJsonFiles("agreements/test_case_14");
    }

    @Test
    @DisplayName("request with country not supported by the system")
    public void testRequest15() throws Exception {
        equalsJsonFiles("agreements/test_case_15");
    }

    @Test
    @DisplayName("request where all fields are null")
    public void testRequest20() throws Exception {
        equalsJsonFiles("agreements/test_case_20");
    }
    @Test
    @DisplayName("request where travel cost null, RISK_TYPE-TRAVEL_CANCELLATION")
    public void testRequest26() throws Exception {
        equalsJsonFiles("agreements/test_case_26");
    }
    @Test
    @DisplayName("request where travel cost null, RISK_TYPE-TRAVEL_MEDICAL")
    public void testRequest27() throws Exception {
        equalsJsonFiles("agreements/test_case_27", true);
    }
}
