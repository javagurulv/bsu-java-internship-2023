package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class TravelCalculatePremiumRequestValidatorTest {
    TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();

    @Test
    void ShouldReturnErrorWhenFirstNameIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("Pard");
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
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    void ShouldntReturnErrorWhenIsPresent(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Alex");
        when(request.getPersonLastName()).thenReturn("Pard");
        List<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void ShouldReturnErrorWhenLastNameIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
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
        when(request.getPersonLastName()).thenReturn("");
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
}
