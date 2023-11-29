package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class
TravelCalculatePremiumServiceImplTest {

    @Test
    public void hasErrorsTest() {
        TravelCalculatePremiumRequestValidator validator = mock(TravelCalculatePremiumRequestValidator.class);
        TravelCalculatePremiumServiceImpl test = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = List.of(new ValidationError("field", "error"));
        when(validator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = test.calculatePremium(request);
        assertTrue(response.hasErrors());
    }
    @Test
    public void validationErrorsTest() {
        TravelCalculatePremiumRequestValidator validator = mock(TravelCalculatePremiumRequestValidator.class);
        TravelCalculatePremiumServiceImpl test = mock(TravelCalculatePremiumServiceImpl.class);
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = List.of(new ValidationError("field", "message"));
        when(validator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = test.calculatePremium(request);

        ValidationError error = errors.get(0);
        assertEquals(error.getField(), "field");
        assertEquals(error.getMessage(), "message");
    }
    @Test
    public void correctReturnResponsesFirstNameTest() {
        //TravelCalculatePremiumRequestValidator validator = mock(TravelCalculatePremiumRequestValidator.class);
        TravelCalculatePremiumServiceImpl test = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date(1000));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000));
        TravelCalculatePremiumResponse response = test.buildResponse(request);
        assertEquals(response.getPersonFirstName(), "FirstName");
    }
    @Test
    public void correctReturnResponsesLastNameTest() {
        TravelCalculatePremiumServiceImpl test = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date(1000));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000));
        TravelCalculatePremiumResponse response = test.buildResponse(request);
        assertEquals(response.getPersonLastName(), "LastName");
    }
    @Test
    public void correctReturnResponsesDateFromTest() {
        TravelCalculatePremiumServiceImpl test = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date(1000));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000));
        //when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = test.buildResponse(request);
        assertEquals(response.getAgreementDateFrom(), new Date(1000));
    }
    @Test
    public void correctReturnResponsesDateToTest() {
        TravelCalculatePremiumServiceImpl test = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date(1000));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000));
        //when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = test.buildResponse(request);
        assertEquals(response.getAgreementDateTo(), new Date(2000));
    }
}