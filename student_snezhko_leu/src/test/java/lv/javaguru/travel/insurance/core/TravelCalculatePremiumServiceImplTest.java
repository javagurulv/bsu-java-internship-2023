package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumServiceImplTest {

    @Test
    public void TravelCalculatePremiumServiceImplTest() {
        TravelCalculatePremiumServiceImpl test = new TravelCalculatePremiumServiceImpl();
        Date date = new Date();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("FirstName", "LastName", date, date);
        TravelCalculatePremiumResponse response = test.calculatePremium(request);
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }
}