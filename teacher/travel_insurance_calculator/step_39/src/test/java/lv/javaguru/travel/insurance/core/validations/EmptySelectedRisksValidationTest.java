package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmptySelectedRisksValidationTest {

    private EmptySelectedRisksValidation validation = new EmptySelectedRisksValidation();

    @Test
    public void shouldReturnErrorWhenSelectedRisksIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(null);
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getField(), "selectedRisks");
        assertEquals(errorOpt.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenSelectedRisksIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of());
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getField(), "selectedRisks");
        assertEquals(errorOpt.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorWhenSelectedRisksIsNotEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isEmpty());
    }

}