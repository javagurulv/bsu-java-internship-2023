package lv.javaguru.travel.insurance.core.core.validations;

import lv.javaguru.travel.insurance.core.validations.PersonFirstNameValidator;
import lv.javaguru.travel.insurance.core.validations.PersonLastNameValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PersonLastNameValidationTest {
    PersonLastNameValidator validator = new PersonLastNameValidator();

    @Test
    public void shouldReturnErrorIfPersonLastNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(null);
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "personLastName");
        assertEquals(errorOptional.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorIfPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("");
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "personLastName");
        assertEquals(errorOptional.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorIfPersonLastNameIsPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("Vorobey");
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isEmpty());
    }
}
