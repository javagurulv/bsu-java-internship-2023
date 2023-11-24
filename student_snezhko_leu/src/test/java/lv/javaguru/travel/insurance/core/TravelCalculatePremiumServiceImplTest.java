package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.RequestValidator;
//import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class
TravelCalculatePremiumServiceImplTest {
    @Mock RequestValidator validator;
    @InjectMocks TravelCalculatePremiumServiceImpl service;
    //TravelCalculatePremiumServiceImpl test;
    @Test
    public void hasErrorsTest() {

        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = List.of(new ValidationError("field", "error"));
        //when(validator.validate(request)).thenReturn(errors);
        doReturn(errors).when(validator).validate(request);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }
    @Test
    public void validationErrorsTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = List.of(new ValidationError("field", "message"));
        when(validator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        ValidationError error = errors.get(0);
        assertEquals(error.getField(), "field");
        assertEquals(error.getMessage(), "message");
    }
    @Test
    public void correctReturnResponsesFirstNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date(1000));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000));
        TravelCalculatePremiumResponse response = service.buildResponse(request);
        assertEquals(response.getPersonFirstName(), "FirstName");
    }
    @Test
    public void correctReturnResponsesLastNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date(1000));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000));
        TravelCalculatePremiumResponse response = service.buildResponse(request);
        assertEquals(response.getPersonLastName(), "LastName");
    }
    @Test
    public void correctReturnResponsesDateFromTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date(1000));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000));
        //when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.buildResponse(request);
        assertEquals(response.getAgreementDateFrom(), new Date(1000));
    }
    @Test
    public void correctReturnResponsesDateToTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date(1000));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000));
        //when(validator.validate(request)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = service.buildResponse(request);
        assertEquals(response.getAgreementDateTo(), new Date(2000));
    }
}