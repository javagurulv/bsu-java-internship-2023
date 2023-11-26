package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SelectedRisksEmptyValidationTest {
    @Mock
    ValidationErrorFactory factory;
    @InjectMocks
    private SelectedRisksEmptyValidation validation;
    @Test
    void shouldReturnErrorWhenSelectedRisksListIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(Collections.emptyList());
        when(factory.buildError("ERROR_CODE_8")).thenReturn(new ValidationError("ERROR_CODE_8", "Selected risks list must not be empty!"));
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getErrorCode()).isEqualTo("ERROR_CODE_8");
        assertThat(validationError.get().getDescription()).isEqualTo("Selected risks list must not be empty!");
    }

    @Test
    void shouldReturnErrorWhenSelectedRisksListIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(null);
        when(factory.buildError("ERROR_CODE_8")).thenReturn(new ValidationError("ERROR_CODE_8", "Selected risks list must not be empty!"));
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getErrorCode()).isEqualTo("ERROR_CODE_8");
        assertThat(validationError.get().getDescription()).isEqualTo("Selected risks list must not be empty!");
    }

    @Test
    void shouldNotReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of("dummy"));
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isEmpty();
    }
}
