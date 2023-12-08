package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.services.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static lv.javaguru.travel.insurance.core.validations.errors.ValidationErrorCodes.MANDATORY_FIELD_MISSING;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SelectedRiskValidationTest {

    @Mock
    ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    SelectedRisksValidation validation;

    @Test
    void testNullValidation() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(null);

        when(validationErrorFactory.buildError(eq(MANDATORY_FIELD_MISSING), argThat(isPlaceHolder())))
                .thenReturn(new ValidationError(MANDATORY_FIELD_MISSING, "description"));

        var error = validation.execute(request);

        assertFalse(error.isEmpty());
        assertEquals(MANDATORY_FIELD_MISSING, error.get().getErrorCode());
        assertEquals("description", error.get().getErrorDescription());
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

    private ArgumentMatcher<Placeholder> isPlaceHolder() {
        return argument -> argument.getKey().equals("fieldName") && argument.getValue().equals("selected_risks");
    }
}
