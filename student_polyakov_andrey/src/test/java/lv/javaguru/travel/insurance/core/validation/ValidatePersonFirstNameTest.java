package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidatePersonFirstNameTest {
    private ValidatePersonFirstName validator = new ValidatePersonFirstName();

    @Test
    public void firstNameShouldNotBeNull() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> errorOptional = validator.validation(reqMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "personFirstName");
        assertEquals(errorOptional.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void firstNameShouldNotBeEmpty() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonFirstName()).thenReturn("");
        Optional<ValidationError> errorOptional = validator.validation(reqMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "personFirstName");
        assertEquals(errorOptional.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsPresent() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonFirstName()).thenReturn("Vasya");
        Optional<ValidationError> errorOptional = validator.validation(reqMock);
        assertTrue(errorOptional.isEmpty());
    }
}
