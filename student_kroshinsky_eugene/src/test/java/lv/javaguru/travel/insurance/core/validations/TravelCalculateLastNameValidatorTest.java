package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculateLastNameValidatorTest {
    @Mock TravelCalculatePremiumRequest request;
    @InjectMocks TravelCalculateLastNameValidator validator;
    @Test
    void validateEmptyLastName() {
        when(request.getPersonLastName()).thenReturn("");
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals("Must not be empty!", validationError.get().getMessage());
        assertEquals("personLastName", validationError.get().getField());
    }
    @Test
    void validateNullLastName() {
        when(request.getPersonLastName()).thenReturn(null);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals("Must not be empty!", validationError.get().getMessage());
        assertEquals("personLastName", validationError.get().getField());
    }
    @Test
    void validateNoErrorsLastName() {
        when(request.getPersonLastName()).thenReturn("personLastName");
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isEmpty());
    }
}