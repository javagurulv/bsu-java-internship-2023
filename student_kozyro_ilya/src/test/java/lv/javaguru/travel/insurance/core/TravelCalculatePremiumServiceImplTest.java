package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.services.DateService;
import lv.javaguru.travel.insurance.core.services.DateServiceImpl;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class TravelCalculatePremiumServiceImplTest {

    DateService dateService = new DateServiceImpl("yyyy-MM-dd");
    TravelCalculatePremiumService service = new TravelCalculatePremiumServiceImpl();
    @Test
    void testCalculatePremium() throws ParseException {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();

        request.setAgreementDateFrom(dateService.createDate("2000-10-10"));
        request.setAgreementDateTo(dateService.createDate("2020-10-10"));
        request.setPersonFirstName("FirstName");
        request.setPersonLastName("LastName");

        var response = service.calculatePremium(request);

        Assertions.assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        Assertions.assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
        Assertions.assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        Assertions.assertEquals(request.getPersonLastName(), response.getPersonLastName());

    }

}