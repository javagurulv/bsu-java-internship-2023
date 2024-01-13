package lv.javaguru.travel.insurance.core.core;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorTest {
    private final TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void testWithNullArgs() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(null);

        List<ValidationError> errors = requestValidator.validate(request);

        assertEquals(errors.size(), 5);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");

        assertEquals(errors.get(1).getField(), "personLastName");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");

        assertEquals(errors.get(2).getField(), "Date from");
        assertEquals(errors.get(2).getMessage(), "Must not be empty!");

        assertEquals(errors.get(3).getField(), "Date to");
        assertEquals(errors.get(3).getMessage(), "Must not be empty!");

        assertEquals(errors.get(4).getField(), "Date to or date from");
        assertEquals(errors.get(4).getMessage(), "Must not be empty!");
    }

    @Test
    public void testWithEmptyArgs() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateTo()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(null);

        List<ValidationError> errors = requestValidator.validate(request);

        assertEquals(errors.size(), 5);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");

        assertEquals(errors.get(1).getField(), "personLastName");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");

        assertEquals(errors.get(2).getField(), "Date from");
        assertEquals(errors.get(2).getMessage(), "Must not be empty!");

        assertEquals(errors.get(3).getField(), "Date to");
        assertEquals(errors.get(3).getMessage(), "Must not be empty!");

        assertEquals(errors.get(4).getField(), "Date to or date from");
        assertEquals(errors.get(4).getMessage(), "Must not be empty!");
    }

    @Test
    public void testWhenDateFromAfterDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(new Date(2005, Calendar.FEBRUARY,1));
        when(request.getAgreementDateFrom()).thenReturn(new Date(2005, Calendar.FEBRUARY,3));

        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 3);

        assertEquals(errors.get(2).getField(), "Date to or date from");
        assertEquals(errors.get(2).getMessage(), "Date to must be after date from");
    }

    @Test
    public void testWhenDateFromSameDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(new Date(2005, Calendar.FEBRUARY,3));
        when(request.getAgreementDateFrom()).thenReturn(new Date(2005, Calendar.FEBRUARY,3));

        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 3);

        assertEquals(errors.get(2).getField(), "Date to or date from");
        assertEquals(errors.get(2).getMessage(), "Date to must be after date from");
    }

    @Test
    public void testWithValidArgs() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("null");
        when(request.getPersonLastName()).thenReturn("null");
        when(request.getAgreementDateTo()).thenReturn(new Date(2005, Calendar.FEBRUARY,5));
        when(request.getAgreementDateFrom()).thenReturn(new Date(2005, Calendar.FEBRUARY,3));

        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 0);
    }



}
