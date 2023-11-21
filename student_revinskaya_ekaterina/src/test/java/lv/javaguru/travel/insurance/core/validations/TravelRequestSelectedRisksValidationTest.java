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


public class TravelRequestSelectedRisksValidationTest {
    @Test
    public void responseShouldContainErrorEmptySelected_risksTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestSelectedRisksValidation selectedRisksValidation = new TravelRequestSelectedRisksValidation();
        when(request.getSelected_risks()).thenReturn(List.of());
        Optional<ValidationError> error= selectedRisksValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "selected_risks");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }
    @Test
    public void responseShouldContainErrorNullSelected_risksTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestSelectedRisksValidation selectedRisksValidation = new TravelRequestSelectedRisksValidation();
        when(request.getSelected_risks()).thenReturn(null);
        Optional<ValidationError> error= selectedRisksValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "selected_risks");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }
}
