package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorManager;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestRisksNotEmptyValidationTest {
    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    ErrorManager errorManager;

    @InjectMocks
    private TravelRequestRisksNotEmptyValidation validation;

    @Test
    public void returnErrorIfRisksAreNull() {
        when(request.getSelectedRisks()).thenReturn(null);
        when(errorManager.getErrorDescription(any())).thenReturn("description");

        Optional<ValidationError> expected = Optional.of(
                new ValidationError("ERROR_CODE_6", "description")
        );
        assertEquals(expected, validation.check(request));
    }

    @Test
    public void returnErrorIfRisksAreNotSelected() {
        when(request.getSelectedRisks()).thenReturn(new ArrayList<>());
        when(errorManager.getErrorDescription(any())).thenReturn("description");

        Optional<ValidationError> expected = Optional.of(
                new ValidationError("ERROR_CODE_6", "description")
        );
        assertEquals(expected, validation.check(request));
    }

    @Test
    public void returnNothingIfRisksAreSelected() {
        when(request.getSelectedRisks()).thenReturn(new ArrayList<>(Arrays.asList(
                "TRAVEL_MEDICAL", "TRAVEL_LOSS_BAGGAGE"
        )));
        assertEquals(Optional.empty(), validation.check(request));
    }
}