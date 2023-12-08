package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class IsEmptySelectedRiskValidationTest {

    @InjectMocks
    SelectedRisksValidation validation;

    @Test
    void testNullValidation() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(null);

        var error = validation.execute(request);

        assertFalse(error.isEmpty());
        assertEquals("selected_risks", error.get().getField());
    }

    @Test
    void testEmptyValidation() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(null);

        var error = validation.execute(request);

        assertFalse(error.isEmpty());
        assertEquals("selected_risks", error.get().getField());
    }

    @Test
    void testOkRisks() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(
                List.of(
                        "TRAVEL_MEDICAL",
                        "TRAVEL_CANCELLATION"
                )
        );

        var error = validation.execute(request);

        assertTrue(error.isEmpty());
    }
}
