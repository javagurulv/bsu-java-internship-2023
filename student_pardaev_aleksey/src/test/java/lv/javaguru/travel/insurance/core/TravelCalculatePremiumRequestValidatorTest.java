package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class TravelCalculatePremiumRequestValidatorTest {
    TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();

    Date CreateDate(String str) {
        try{
            DateFormat a = new SimpleDateFormat("dd.MM.yyyy");
            return a.parse(str);
        }catch (ParseException e){
            throw new RuntimeException();
        }
    }

    @Test
    void ShouldReturnErrorWhenFirstNameIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("Pard");
        when(request.getAgreementDateTo()).thenReturn(CreateDate("21.06.2025"));
        when(request.getAgreementDateFrom()).thenReturn(CreateDate("21.02.2025"));
        when(request.getPersonFirstName()).thenReturn(null);
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    void ShouldReturnErrorWhenFirstNameIsEmpty(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getAgreementDateTo()).thenReturn(CreateDate("21.06.2025"));
        when(request.getAgreementDateFrom()).thenReturn(CreateDate("21.02.2025"));
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    void ShouldntReturnErrorWhenIsPresent(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Alex");
        when(request.getAgreementDateTo()).thenReturn(CreateDate("21.06.2025"));
        when(request.getAgreementDateFrom()).thenReturn(CreateDate("21.02.2025"));
        when(request.getPersonLastName()).thenReturn("Pard");
        List<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void ShouldReturnErrorWhenLastNameIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(CreateDate("21.06.2025"));
        when(request.getAgreementDateFrom()).thenReturn(CreateDate("21.02.2025"));
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getPersonFirstName()).thenReturn("Alex");
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    void ShouldReturnErrorWhenLastNameIsEmpty(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Alex");
        when(request.getAgreementDateTo()).thenReturn(CreateDate("21.06.2025"));
        when(request.getAgreementDateFrom()).thenReturn(CreateDate("21.02.2025"));
        when(request.getPersonLastName()).thenReturn("");
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    void ShouldReturnErrorWhenAgreementDateFromIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Alex");
        when(request.getAgreementDateTo()).thenReturn(CreateDate("21.06.2025"));
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("Pard");
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.get(0).getField(), "agreementDateFrom");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    void ShouldReturnErrorWhenAgreementDateToIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("firstName");
        when(request.getPersonLastName()).thenReturn("lastName");
        when(request.getAgreementDateFrom()).thenReturn(CreateDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "agreementDateTo");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    void ShouldReturnErrorWhenDateToNotAfterDateFrom() throws ParseException {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Alex");
        when(request.getPersonLastName()).thenReturn("Pard");
        DateFormat a = new SimpleDateFormat("dd.MM.yyyy");
        when(request.getAgreementDateFrom()).thenReturn(a.parse("21.02.2025"));
        when(request.getAgreementDateTo()).thenReturn(a.parse("21.01.2025"));

        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.get(0).getField(), "agreementDateTo");
        assertEquals(errors.get(0).getMessage(), "Must be after agreementDateFrom!");
    }
}
