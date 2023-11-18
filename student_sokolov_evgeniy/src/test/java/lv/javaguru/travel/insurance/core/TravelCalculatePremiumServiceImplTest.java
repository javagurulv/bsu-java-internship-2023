package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void testCalculatePremium() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        request.setPersonLastName("Doe");

        Date agreementDateFrom = Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date agreementDateTo = Date.from(LocalDate.of(2023, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant());

        request.setAgreementDateFrom(agreementDateFrom);
        request.setAgreementDateTo(agreementDateTo);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals("John", response.getPersonFirstName());
        assertEquals("Doe", response.getPersonLastName());
        assertEquals(null, response.getAgreementPrice());

        BigDecimal expectedAgreementPrice = new BigDecimal("100.50");
        response.setAgreementPrice(expectedAgreementPrice);
        assertEquals(expectedAgreementPrice, response.getAgreementPrice());
    }
}
