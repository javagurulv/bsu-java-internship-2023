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

    private TravelCalculatePremiumServiceImpl test = new TravelCalculatePremiumServiceImpl();
    private TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
    private TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("FirstName", "LastName", new Date(1500), new Date(2000));
    @Test
    public void TravelCalculatePremiumServiceImplChangeResponsePersonFirstNameTest() {
        test.changeResponsePersonFirstName(request, response);
        assertEquals(response.getPersonFirstName(), "FirstName");
    }
    @Test
    public void TravelCalculatePremiumServiceImplChangeResponsePersonLastNameTest() {
        test.changeResponsePersonLastName(request, response);
        assertEquals(response.getPersonLastName(), "LastName");
    }
    @Test
    public void TravelCalculatePremiumServiceImplChangeResponseAgreementDateFromTest() {
        test.changeResponseAgreementDateFrom(request, response);
        assertEquals(response.getAgreementDateFrom(), new Date(1500));
    }
    @Test
    public void TravelCalculatePremiumServiceImplChangeResponseAgreementDateToTest() {
        test.changeResponseAgreementDateTo(request, response);
        assertEquals(response.getAgreementDateTo(), new Date(2000));
    }
}