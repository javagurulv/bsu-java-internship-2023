package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TravelCalculatePremiumServiceImplTest {
    @Test
    public void testResponseFields() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = sdf.parse("25/01/2022");
        Date date2 = sdf.parse("25/12/2022");
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Andrey", "Polyakov", date1, date2);
        TravelCalculatePremiumService calculatePremiumService = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

}