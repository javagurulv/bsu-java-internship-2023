package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void fromRequestToResponseFields() throws ParseException {
        SimpleDateFormat a = new SimpleDateFormat("dd.MM.yyyy");
        Date dateFrom = a.parse("01.06.2023");
        Date dateTo = a.parse("01.07.2023");
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Aleksey", "Pardaev", dateFrom, dateTo);
        TravelCalculatePremiumService test = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumResponse response = test.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

}