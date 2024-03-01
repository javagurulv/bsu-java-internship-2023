package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidatePersonLastNameTest {
    private ValidatePersonLastName validator = new ValidatePersonLastName();

    @Test
    public void lastNameShouldNotBeNull() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonLastName()).thenReturn(null);
        Optional<ValidationError> errorOptional = validator.validation(reqMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "personLastName");
        assertEquals(errorOptional.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void lastNameShouldNotBeEmpty() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonLastName()).thenReturn("");
        Optional<ValidationError> errorOptional = validator.validation(reqMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "personLastName");
        assertEquals(errorOptional.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsPresent() {
        TravelCalculatePremiumRequest reqMock = mock(TravelCalculatePremiumRequest.class);
        when(reqMock.getPersonLastName()).thenReturn("Vasya");
        Optional<ValidationError> errorOptional = validator.validation(reqMock);
        assertTrue(errorOptional.isEmpty());
    }
}
