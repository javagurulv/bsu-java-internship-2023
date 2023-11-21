package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TravelUnterwritingTest {

    @Test
    public void calculatePremiumTest() {
        TravelCalculatePremiumResponse response = mock(TravelCalculatePremiumResponse.class);
        when(response.getAgreementDateTo()).thenReturn(Date.valueOf("2022-09-12"));
        when(response.getAgreementDateFrom()).thenReturn(Date.valueOf("2022-09-11"));
        TravelUnderwriting underwriting = new TravelUnderwriting();
        assertEquals(underwriting.calculatePremium(response), BigDecimal.valueOf(86400000));
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
