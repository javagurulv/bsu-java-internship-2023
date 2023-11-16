package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorTest {
    @Test
    public void firstNameShouldNotBeEmpty() {
        TravelCalculatePremiumRequestValidator reqVal = new TravelCalculatePremiumRequestValidator();
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonFirstName()).thenReturn("");
        when(reqMock.getPersonLastName()).thenReturn("Pupkin");
        when(reqMock.getAgreementDateFrom()).thenReturn(new Date());
        when(reqMock.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errorsList = reqVal.validate(reqMock);
        assertFalse(errorsList.isEmpty());
        assertEquals(errorsList.get(0).getField(), "personFirstName");
        assertEquals(errorsList.get(0).getMessage(), "Must not be empty!");
        assertEquals(errorsList.size(), 1);
    }

    @Test
    public void firstNameShouldNotBeNull() {
        TravelCalculatePremiumRequestValidator reqVal = new TravelCalculatePremiumRequestValidator();
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonFirstName()).thenReturn(null);
        when(reqMock.getPersonLastName()).thenReturn("Pupkin");
        when(reqMock.getAgreementDateFrom()).thenReturn(new Date());
        when(reqMock.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errorsList = reqVal.validate(reqMock);
        assertFalse(errorsList.isEmpty());
        assertEquals(errorsList.get(0).getField(), "personFirstName");
        assertEquals(errorsList.get(0).getMessage(), "Must not be empty!");
        assertEquals(errorsList.size(), 1);
    }

    @Test
    public void lastNameShouldNotBeEmpty() {
        TravelCalculatePremiumRequestValidator reqVal = new TravelCalculatePremiumRequestValidator();
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonFirstName()).thenReturn("Vasya");
        when(reqMock.getPersonLastName()).thenReturn("");
        when(reqMock.getAgreementDateFrom()).thenReturn(new Date());
        when(reqMock.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errorsList = reqVal.validate(reqMock);
        assertFalse(errorsList.isEmpty());
        assertEquals(errorsList.get(0).getField(), "personLastName");
        assertEquals(errorsList.get(0).getMessage(), "Must not be empty!");
        assertEquals(errorsList.size(), 1);
    }

    @Test
    public void lastNameShouldNotBeNull() {
        TravelCalculatePremiumRequestValidator reqVal = new TravelCalculatePremiumRequestValidator();
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonFirstName()).thenReturn("Vasya");
        when(reqMock.getPersonLastName()).thenReturn(null);
        when(reqMock.getAgreementDateFrom()).thenReturn(new Date());
        when(reqMock.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errorsList = reqVal.validate(reqMock);
        assertFalse(errorsList.isEmpty());
        assertEquals(errorsList.get(0).getField(), "personLastName");
        assertEquals(errorsList.get(0).getMessage(), "Must not be empty!");
        assertEquals(errorsList.size(), 1);
    }

    @Test
    public void agreementDateFromShouldNotBeNull() {
        TravelCalculatePremiumRequestValidator reqVal = new TravelCalculatePremiumRequestValidator();
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonFirstName()).thenReturn("Vasya");
        when(reqMock.getPersonLastName()).thenReturn("Pupkin");
        when(reqMock.getAgreementDateFrom()).thenReturn(null);
        when(reqMock.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errorsList = reqVal.validate(reqMock);
        assertFalse(errorsList.isEmpty());
        assertEquals(errorsList.get(0).getField(), "agreementDateFrom");
        assertEquals(errorsList.get(0).getMessage(), "Must not be empty!");
        assertEquals(errorsList.size(), 1);
    }

    @Test
    public void agreementDateToShouldNotBeNull() {
        TravelCalculatePremiumRequestValidator reqVal = new TravelCalculatePremiumRequestValidator();
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonFirstName()).thenReturn("Vasya");
        when(reqMock.getPersonLastName()).thenReturn("Pupkin");
        when(reqMock.getAgreementDateFrom()).thenReturn(new Date());
        when(reqMock.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errorsList = reqVal.validate(reqMock);
        assertFalse(errorsList.isEmpty());
        assertEquals(errorsList.get(0).getField(), "agreementDateTo");
        assertEquals(errorsList.get(0).getMessage(), "Must not be empty!");
        assertEquals(errorsList.size(), 1);
    }

    @Test
    public void errorShouldNotBeCalledWhenFirstLastNameIsPresent() {
        TravelCalculatePremiumRequestValidator reqVal = new TravelCalculatePremiumRequestValidator();
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonFirstName()).thenReturn("Vasya");
        when(reqMock.getPersonLastName()).thenReturn("Pupkin");
        when(reqMock.getAgreementDateFrom()).thenReturn(new Date());
        when(reqMock.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errorsList = reqVal.validate(reqMock);
        assertTrue(errorsList.isEmpty());
    }
}
