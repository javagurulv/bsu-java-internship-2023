package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
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
    public void responseShouldContainErrorEmptyNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
    @Test
    public void responseShouldContainErrorNullNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
    @Test
    public void responseNotContainErrorTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Misha");
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 0);
    }

}