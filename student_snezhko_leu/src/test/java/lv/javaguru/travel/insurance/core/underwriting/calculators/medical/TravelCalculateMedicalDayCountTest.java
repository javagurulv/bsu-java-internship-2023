package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;


import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculateMedicalDayCountTest {
    private TravelCalculateDayCount calculator = new TravelCalculateDayCount();

    TravelCalculatePremiumRequestV1 request;

    @Test
    public void calculatorMedicalDayCountTest() {
        init();
        assertEquals(3, calculator.calculatePremium(request));
    }
    private void init() {
        request = mock(TravelCalculatePremiumRequestV1.class);

        //Date dateTo = new Date();
        //Date dateFrom = new Date(dateTo.getYear(), dateTo.getMonth(), dateTo.getDay() - dayCount);
        //dateFrom.setDate(dateFrom.getDay() - dayCount);


        when(request.getAgreementDateTo()).thenReturn(Date.valueOf("2030-05-05"));
        when(request.getAgreementDateFrom()).thenReturn(Date.valueOf("2030-05-02"));
    }
}
