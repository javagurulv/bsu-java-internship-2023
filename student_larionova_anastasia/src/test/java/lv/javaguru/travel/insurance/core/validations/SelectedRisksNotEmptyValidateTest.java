package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
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

    private SelectedRisksNotEmptyValidate validate = new SelectedRisksNotEmptyValidate();

    @Test
    void validatorWhenSelectedRisksIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(null);
        Optional<ValidationError> errors = validate.validator(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_8");
        assertEquals(errors.get().getDescription(), validate.errorCode8Message);
    }

    @Test
    void validatorWhenSelectedRisksIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(Collections.emptyList());
        Optional<ValidationError> errors = validate.validator(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_8");
        assertEquals(errors.get().getDescription(), validate.errorCode8Message);
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