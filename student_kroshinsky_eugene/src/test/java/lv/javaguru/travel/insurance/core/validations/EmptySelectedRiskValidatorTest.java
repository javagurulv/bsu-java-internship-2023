package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmptySelectedRiskValidatorTest {
    @Mock private ValidationError expectedError;
    @Mock private ValidationErrorFactory validationErrorFactory;
    @Mock private TravelCalculatePremiumRequest request;
    @InjectMocks private EmptySelectedRiskValidator validator;
    @Test
    void injectedRepositoryAreNotNull() {
        assertNotNull(validationErrorFactory);
        assertNotNull(request);
        assertNotNull(validator);
        assertNotNull(expectedError);
    }
    @Test
    void validateNullSelectedRiskTest() {
        when(request.getSelectedRisks()).thenReturn(null);
        when(validationErrorFactory.createValidationError("ERROR_CODE_7")).thenReturn(expectedError);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals(expectedError, validationError.get());
    }
    @Test
    void validateEmptySelectedRiskTest() {
        when(request.getSelectedRisks()).thenReturn(List.of());
        when(validationErrorFactory.createValidationError("ERROR_CODE_7")).thenReturn(expectedError);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals(expectedError, validationError.get());
    }

    @Test
    void validateNoErrorsSelectedRiskTest() {
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"));
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isEmpty());
    }
}