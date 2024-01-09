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
    private ValidationErrorFactory validationErrorFactory;
    @Mock
    private TravelCalculatePremiumRequest request;
    @InjectMocks
    private TravelCalculateFirstNameValidator validator;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(validationErrorFactory);
        assertNotNull(request);
        assertNotNull(validator);
    }

    @Test
    void validateEmptyFirstName() {
        when(request.getPersonFirstName()).thenReturn("");
        ValidationError expectedError = new ValidationError("ERROR_CODE", "Description");
        when(validationErrorFactory.createValidationError("ERROR_CODE_5")).thenReturn(expectedError);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals("Description", validationError.get().getDescription());
        assertEquals("ERROR_CODE", validationError.get().getErrorCode());
    }

    @Test
    void validateNullFirstName() {
        when(request.getPersonFirstName()).thenReturn(null);
        ValidationError expectedError = new ValidationError("ERROR_CODE", "Description");
        when(validationErrorFactory.createValidationError("ERROR_CODE_5")).thenReturn(expectedError);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals("Description", validationError.get().getDescription());
        assertEquals("ERROR_CODE", validationError.get().getErrorCode());
    }

    @Test
    void validateNoErrorsFirstName() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isEmpty());
    }
}