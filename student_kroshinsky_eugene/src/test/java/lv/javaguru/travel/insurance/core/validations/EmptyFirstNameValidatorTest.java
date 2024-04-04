package lv.javaguru.travel.insurance.core.validations;


import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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
class EmptyFirstNameValidatorTest {
    @Mock private ValidationError expectedError;
    @Mock private ValidationErrorFactory validationErrorFactory;
    @Mock private TravelCalculatePremiumRequestV1 request;
    @InjectMocks private EmptyFirstNameValidator validator;
    @Test
    void injectedRepositoryAreNotNull() {
        assertNotNull(validationErrorFactory);
        assertNotNull(request);
        assertNotNull(validator);
        assertNotNull(expectedError);
    }

    @Test
    void validateEmptyFirstNameTest() {
        when(request.getPersonFirstName()).thenReturn("");
        when(validationErrorFactory.createValidationError("ERROR_CODE_5")).thenReturn(expectedError);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals(expectedError, validationError.get());
    }

    @Test
    void validateNullFirstNameTest() {
        when(request.getPersonFirstName()).thenReturn(null);
        when(validationErrorFactory.createValidationError("ERROR_CODE_5")).thenReturn(expectedError);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals(expectedError, validationError.get());
    }

    @Test
    void validateNoErrorsFirstNameTest() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isEmpty());
    }
}