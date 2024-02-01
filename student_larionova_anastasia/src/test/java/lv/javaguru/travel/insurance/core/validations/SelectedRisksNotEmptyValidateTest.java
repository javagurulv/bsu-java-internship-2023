package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class SelectedRisksNotEmptyValidateTest {

    @InjectMocks
    SelectedRisksNotEmptyValidate validate;

    @Mock private ValidationErrorFactory errorFactory;

    @Test
    void validatorWhenSelectedRisksIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_8")).thenReturn(validationError);
        Optional<ValidationError> errors = validate.validator(request);
        assertFalse(errors.isEmpty());
        assertSame(errors.get(), validationError);
    }

    @Test
    void validatorWhenSelectedRisksIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(Collections.emptyList());
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_8")).thenReturn(validationError);
        Optional<ValidationError> errors = validate.validator(request);
        assertFalse(errors.isEmpty());
        assertSame(errors.get(), validationError);
    }

    @Test
    void validatorWhenSelectedRisksIsExist() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<String> selectedRisks = new ArrayList<>();
        selectedRisks.add("TRAVEL_MEDICAL");
        when(request.getSelectedRisks()).thenReturn(new ArrayList<>(selectedRisks));
        Optional<ValidationError> errors = validate.validator(request);
        assertTrue(errors.isEmpty());
    }
}