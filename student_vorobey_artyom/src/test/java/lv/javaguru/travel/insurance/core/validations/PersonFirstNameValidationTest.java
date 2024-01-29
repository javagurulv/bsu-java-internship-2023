package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.validations.PersonFirstNameValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PersonFirstNameValidationTest {
    PersonFirstNameValidator validator = new PersonFirstNameValidator();
    @Test
    public void shouldReturnErrorIfPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "personFirstName");
        assertEquals(errorOptional.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorIfPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "personFirstName");
        assertEquals(errorOptional.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorIfPersonFirstNameIsPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Artyom");
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isEmpty());
    }
}
