package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelCalculatePremiumRequestValidatorTest {

    private final TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
    GregorianCalendar calendar = new GregorianCalendar();
    @Test
    void shouldBeValidRequestIfFirstLastNameAndAgreementDatesArePresent()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("14-04-2005"));
        when(request.getAgreementDateTo()).thenReturn(createDate("14-04-2006"));
        List<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldBeInvalidRequestIfFirstNameIsEmpty()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("14-04-2005"));
        when(request.getAgreementDateTo()).thenReturn(createDate("14-04-2006"));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    void shouldBeInvalidRequestIfLastNameIsEmpty()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(createDate("14-04-2005"));
        when(request.getAgreementDateTo()).thenReturn(createDate("14-04-2006"));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("personLastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    void shouldBeInvalidRequestIfFirstNameIsNull()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("14-04-2005"));
        when(request.getAgreementDateTo()).thenReturn(createDate("14-04-2006"));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    void shouldBeInvalidRequestIfLastNameIsNull()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(createDate("14-04-2005"));
        when(request.getAgreementDateTo()).thenReturn(createDate("14-04-2006"));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("personLastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    void shouldBeInvalidRequestIfDateAgreementFromIsNull()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    void shouldBeInvalidRequestIfDateAgreementToIsNull()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("agreementDateTo", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    void shouldBeInvalidRequestIfDateAgreementFromIsAfterDateAgreementTo()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("14-04-2005"));
        when(request.getAgreementDateTo()).thenReturn(createDate("14-04-2004"));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.get(0).getField());
        assertEquals("Must be before the agreementDateTo!", errors.get(0).getMessage());
    }

    @Test
    void shouldBeInvalidRequestIfDateAgreementFromEqualsDateAgreementTo()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("14-04-2005"));
        when(request.getAgreementDateTo()).thenReturn(createDate("14-04-2005"));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.get(0).getField());
        assertEquals("Must be before the agreementDateTo!", errors.get(0).getMessage());
    }

    Date createDate(String stringDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try{
            return  formatter.parse(stringDate);
        }
        catch(ParseException e)
        {
            throw new RuntimeException();
        }
    }
}


