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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculateSelectedRiskValidatorTest {
    @Mock
    TravelCalculatePremiumRequest request;
    @InjectMocks
    TravelCalculateSelectedRiskValidator validator;
    @Test
    void validateNullSelectedRisk() {
        when(request.getSelectedRisks()).thenReturn(null);
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals("Must not be empty!", validationError.get().getMessage());
        assertEquals("selectedRisk", validationError.get().getField());
    }
    @Test
    void validateEmptySelectedRisk() {
        when(request.getSelectedRisks()).thenReturn(List.of());
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isPresent());
        assertEquals("Must not be empty!", validationError.get().getMessage());
        assertEquals("selectedRisk", validationError.get().getField());
    }

    @Test
    void validateNoErrorsSelectedRisk() {
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"));
        Optional<ValidationError> validationError = validator.validate(request);
        assertTrue(validationError.isEmpty());
    }
}