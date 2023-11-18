package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void testCalculatePremium() {
        TravelCalculatePremiumService calculatePremiumService = new TravelCalculatePremiumServiceImpl();

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("John",
                "Doe", new Date(), new Date());
        TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);

        assertEquals("John", response.getPersonFirstName());
        assertEquals("Doe", response.getPersonLastName());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }
}
