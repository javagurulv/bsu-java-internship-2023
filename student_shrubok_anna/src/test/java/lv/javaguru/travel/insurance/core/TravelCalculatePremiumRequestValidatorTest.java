package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelCalculatePremiumRequestValidatorTest {

    private final TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
    @Test
    void shouldBeValidRequestIfFirstAndLastNameArePresent()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        List<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldBeInvalidRequestIfFirstNameIsEmpty()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("LastName");
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
        List<ValidationError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("personLastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

}


