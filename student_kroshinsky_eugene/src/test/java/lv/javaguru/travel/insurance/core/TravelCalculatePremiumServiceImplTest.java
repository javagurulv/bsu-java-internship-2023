package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumServiceImplTest {
    private static TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();;
    @Test
    public void calculatePremiumTest() {
        TravelCalculatePremiumServiceImpl calculator = new TravelCalculatePremiumServiceImpl();

        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Eugene");
        when(request.getPersonLastName()).thenReturn("Kroshinsky");
        when(request.getAgreementDateFrom()).thenReturn(new Date(2023, 11, 16));
        when(request.getAgreementDateTo()).thenReturn(new Date(2023, 11, 24));

        TravelCalculatePremiumResponse response = calculator.calculatePremium(request);

        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
        assertEquals(new BigDecimal(8), response.getAgreementPrice());
    }


}