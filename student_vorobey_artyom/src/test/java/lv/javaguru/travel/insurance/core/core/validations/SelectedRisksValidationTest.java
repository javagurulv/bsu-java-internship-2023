package lv.javaguru.travel.insurance.core.core.validations;

import lv.javaguru.travel.insurance.core.validations.SelectedRisksValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SelectedRisksValidationTest {
    SelectedRisksValidator validator = new SelectedRisksValidator();

    @Test
    public void shouldReturnErrorIfSelectedRisksIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(new ArrayList<>());
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "selectedRisks");
        assertEquals(errorOptional.get().getMessage(), "Must not be empty!");
    }
    
    @Test
    public void shouldReturnErrorIfSelectedRisksIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(null);
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "selectedRisks");
        assertEquals(errorOptional.get().getMessage(), "Must not be empty!");
    }

}
