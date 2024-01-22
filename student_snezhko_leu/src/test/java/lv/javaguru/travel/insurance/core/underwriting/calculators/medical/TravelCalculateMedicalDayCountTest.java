package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;


import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculateMedicalDayCountTest {
    private TravelCalculateDayCount calculator = new TravelCalculateDayCount();

    TravelCalculatePremiumRequest request;

    @Test
    public void calculatorMedicalDayCountTest() {
        init();
        assertEquals(3, calculator.calculatePremium(request));
    }
    private void init() {
        request = mock(TravelCalculatePremiumRequest.class);

        //Date dateTo = new Date();
        //Date dateFrom = new Date(dateTo.getYear(), dateTo.getMonth(), dateTo.getDay() - dayCount);
        //dateFrom.setDate(dateFrom.getDay() - dayCount);


        when(request.getAgreementDateTo()).thenReturn(Date.valueOf("2030-05-05"));
        when(request.getAgreementDateFrom()).thenReturn(Date.valueOf("2030-05-02"));
    }
}
