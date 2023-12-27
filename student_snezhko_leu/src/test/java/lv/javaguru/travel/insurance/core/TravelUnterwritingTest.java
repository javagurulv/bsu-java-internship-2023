package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.InsurancePremiumRisk;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TravelUnterwritingTest {

    @Test
    public void calculatePremiumTest() {
        //ArrayList<String> risks = new ArrayList<>();
        //risks.add("MEDICAL RISK");
        TravelCalculatePremiumResponse response = mock(TravelCalculatePremiumResponse.class);
       when(response.getAgreementDateTo()).thenReturn(Date.valueOf("2022-09-12"));
       when(response.getAgreementDateFrom()).thenReturn(Date.valueOf("2022-09-11"));
       //when(response.getSelected_risks()).thenReturn(risks);
        TravelUnderwriting underwriting = new TravelUnderwriting();
        assertEquals(underwriting.calculatePremium(response), BigDecimal.valueOf(1));
    }
/*
    @Test
    public void ToLargeThenFromAgreementTest() {
        TravelCalculatePremiumResponse response = mock(TravelCalculatePremiumResponse.class);

        when(response.getAgreementDateFrom()).thenReturn(Date.valueOf("11.09.2022"));
        when(response.getAgreementDateFrom()).thenReturn(Date.valueOf("13.09.2022"));

        assertEquals(TravelUnderwriting.getAgreementPrice(Date.valueOf("2022-09-12"), Date.valueOf("2022-09-11")), BigDecimal.valueOf(86400000));
    }
    */
}
