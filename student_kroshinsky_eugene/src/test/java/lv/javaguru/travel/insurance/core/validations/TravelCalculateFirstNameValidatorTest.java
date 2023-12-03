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
class TravelCalculateFirstNameValidatorTest {
    @Mock
    PropertyReader propertyReader;
    @Mock
    TravelCalculatePremiumRequest request;
    @InjectMocks
    TravelCalculateFirstNameValidator validator;

    @Test
    void validateEmptyFirstName() {
        when(request.getPersonFirstName()).thenReturn("");
        when(propertyReader.getProperty("ERROR_CODE_5")).thenReturn("Field personFirstName is empty!");
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals("Field personFirstName is empty!", validationError.get().getDescription());
        assertEquals("ERROR_CODE_5", validationError.get().getErrorCode());
    }

    @Test
    void validateNullFirstName() {
        when(request.getPersonFirstName()).thenReturn(null);
        when(propertyReader.getProperty("ERROR_CODE_5")).thenReturn("Field personFirstName is empty!");
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals("Field personFirstName is empty!", validationError.get().getDescription());
        assertEquals("ERROR_CODE_5", validationError.get().getErrorCode());
    }

    @Test
    void validateNoErrorsFirstName() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isEmpty());
    }
}