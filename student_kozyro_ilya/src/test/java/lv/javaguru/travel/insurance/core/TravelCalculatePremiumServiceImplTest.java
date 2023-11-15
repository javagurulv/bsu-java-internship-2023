package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.services.DateService;
import lv.javaguru.travel.insurance.core.services.DateServiceImpl;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TravelCalculatePremiumServiceImplTest {

    @Autowired
    DateService dateService;

    @Autowired
    TravelCalculatePremiumServiceImpl travelCalculatePremiumService;
    @Test
    void testCalculatePremium() throws ParseException {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();

        request.setAgreementDateFrom(dateService.createDate("2000-10-10"));
        request.setAgreementDateTo(dateService.createDate("2020-10-10"));
        request.setPersonFirstName("FirstName");
        request.setPersonLastName("LastName");

        var response = travelCalculatePremiumService.calculatePremium(request);

        Assertions.assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        Assertions.assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
        Assertions.assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        Assertions.assertEquals(request.getPersonLastName(), response.getPersonLastName());

    }

    @Test
    void badFormatTest() throws ParseException {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();

        Exception exception = assertThrows(ParseException.class, () -> {
            request.setAgreementDateFrom(dateService.createDate("Strange Data"));
        });

        assertSame(exception.getClass(), ParseException.class);

    }

    @Test
    void badFormatTest2() throws ParseException {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();

        Exception exception = assertThrows(ParseException.class, () -> {
            request.setAgreementDateFrom(dateService.createDate("2000.10.10"));
        });

        assertSame(exception.getClass(), ParseException.class);

    }

}