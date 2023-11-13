package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumServiceImplTest {

    TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void responseShouldContainErrorEmptyFirstNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("Frolov");
        Calendar data = new GregorianCalendar();
        data.set(2018, Calendar.JULY, 8);
        when(request.getAgreementDateFrom()).thenReturn(data.getTime());
        data.set(2018, Calendar.AUGUST, 18);
        when(request.getAgreementDateTo()).thenReturn(data.getTime());
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
    @Test
    public void responseShouldContainErrorNullFirstNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("Ignatov");
        Calendar data = new GregorianCalendar();
        data.set(2018, Calendar.JULY, 8);
        when(request.getAgreementDateFrom()).thenReturn(data.getTime());
        data.set(2018, Calendar.AUGUST, 18);
        when(request.getAgreementDateTo()).thenReturn(data.getTime());
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
    @Test
    public void responseShouldContainErrorEmptyLastNameTest(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Sasha");
        when(request.getPersonLastName()).thenReturn("");
        Calendar data = new GregorianCalendar();
        data.set(2018, Calendar.JULY, 8);
        when(request.getAgreementDateFrom()).thenReturn(data.getTime());
        data.set(2018, Calendar.AUGUST, 18);
        when(request.getAgreementDateTo()).thenReturn(data.getTime());
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
    @Test
    public void responseShouldContainErrorNullLastNameTest(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Nikita");
        when(request.getPersonLastName()).thenReturn(null);
        Calendar data = new GregorianCalendar();
        data.set(2018, Calendar.JULY, 8);
        when(request.getAgreementDateFrom()).thenReturn(data.getTime());
        data.set(2018, Calendar.AUGUST, 18);
        when(request.getAgreementDateTo()).thenReturn(data.getTime());
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
    @Test
    public void responseShouldContainErrorNullDateFromTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Misha");
        when(request.getPersonLastName()).thenReturn("Popov");
        when(request.getAgreementDateFrom()).thenReturn(null);
        Calendar data = new GregorianCalendar();
        data.set(2018, Calendar.AUGUST, 18);
        when(request.getAgreementDateTo()).thenReturn(data.getTime());
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(),"agreementDateFrom" );
        assertEquals(errors.get(0).getMessage(),"Must not be empty!" );
    }
    @Test
    public void responseShouldContainErrorNullDateToTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Misha");
        when(request.getPersonLastName()).thenReturn("Popov");
        when(request.getAgreementDateFrom()).thenReturn(null);
        Calendar data = new GregorianCalendar();
        data.set(2018, Calendar.AUGUST, 18);
        when(request.getAgreementDateFrom()).thenReturn(data.getTime());
        when(request.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(),"agreementDateTo" );
        assertEquals(errors.get(0).getMessage(),"Must not be empty!" );
    }
    @Test
    public void responseShouldContainErrorDateFromMoreToTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Misha");
        when(request.getPersonLastName()).thenReturn("Popov");
        when(request.getAgreementDateFrom()).thenReturn(null);
        Calendar data = new GregorianCalendar();
        data.set(2018, Calendar.AUGUST, 18);
        when(request.getAgreementDateFrom()).thenReturn(data.getTime());
        data.set(2017, Calendar.AUGUST, 18);
        when(request.getAgreementDateTo()).thenReturn(data.getTime());
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(),"agreementDateFrom" );
        assertEquals(errors.get(0).getMessage(),"agreementDateFrom must be less than agreementDateTo!" );
    }
    @Test
    public void responseNotContainErrorTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Misha");
        when(request.getPersonLastName()).thenReturn("Popov");
        Calendar data = new GregorianCalendar();
        data.set(2018, Calendar.JULY, 8);
        when(request.getAgreementDateFrom()).thenReturn(data.getTime());
        data.set(2018, Calendar.AUGUST, 18);
        when(request.getAgreementDateTo()).thenReturn(data.getTime());
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 0);
    }

}