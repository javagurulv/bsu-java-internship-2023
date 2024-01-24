package lv.javaguru.travel.insurance.rest.v2.agreement;

import lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgreementValidationV2TestCases extends TravelCalculatePremiumControllerV2Test {
    @Test
    @DisplayName("request with null agreement date from")
    public void testRequest1() throws Exception {
        equalsJsonFiles("agreements/ERROR_CODE_3_null_agreement_date_from");
    }

    @Test
    @DisplayName("request with null agreement date to")
    public void testRequest2() throws Exception {
        equalsJsonFiles("agreements/ERROR_CODE_4_null_agreement_date_to");
    }
    @Test
    @DisplayName("request with agreement date from in the past")
    public void testRequest3() throws Exception {
        equalsJsonFiles("agreements/ERROR_CODE_5_agreement_date_from_in_the_past");
    }


    @Test
    @DisplayName("request with agreement date to in the past")
    public void testRequest4() throws Exception {
        equalsJsonFiles("agreements/ERROR_CODE_6_agreement_date_to_in_the_past");
    }

    @Test
    @DisplayName("request with agreement date to less than agreement date from")
    public void testRequest5() throws Exception {
        equalsJsonFiles("agreements/ERROR_CODE_7_agreement_date_to_less_than_from");
    }

    @Test
    @DisplayName("request with null country")
    public void testRequest6() throws Exception {
        equalsJsonFiles("agreements/ERROR_CODE_10_null_country");
    }

    @Test
    @DisplayName("request with empty country")
    public void testRequest7() throws Exception {
        equalsJsonFiles("agreements/ERROR_CODE_10_empty_country");
    }

    @Test
    @DisplayName("request with country not supported by the system, RISK_TYPE - MEDICAL_RISK")
    public void testRequest8() throws Exception {
        equalsJsonFiles("agreements/ERROR_CODE_11_country_not_supported_by_system");
    }

    @Test
    @DisplayName("request with country not supported by the system, RISK_TYPE - TRAVEL_CANCELLATION selected")
    public void testRequest9() throws Exception {
        equalsJsonFiles("agreements/ERROR_CODE_11_country_not_supported_by_system_not_medical_risk");
    }

    @Test
    @DisplayName("request with empty list of persons")
    public void testRequest10() throws Exception {
        equalsJsonFiles("agreements/ERROR_CODE_16_empty_list_of_persons");
    }

    @Test
    @DisplayName("request with null list of persons")
    public void testRequest11() throws Exception {
        equalsJsonFiles("agreements/ERROR_CODE_16_null_list_of_persons");
    }
    @Test
    @DisplayName("request where travel cost null, RISK_TYPE-TRAVEL_CANCELLATION")
    public void testRequest12() throws Exception {
        equalsJsonFiles("agreements/ERROR_CODE_21_travel_cost_null");
    }
    @Test
    @DisplayName("request where travel cost null, RISK_TYPE-TRAVEL_MEDICAL")
    public void testRequest13() throws Exception {
        equalsJsonFiles("agreements/travel_cost_null_medical_risk", true);
    }
    @Test
    @DisplayName("request with null fields (one person)")
    public void testRequest14() throws Exception {
        equalsJsonFiles("agreements/all_fields_null");
    }

}