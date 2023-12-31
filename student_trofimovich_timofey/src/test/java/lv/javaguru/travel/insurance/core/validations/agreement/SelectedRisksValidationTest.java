package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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
public class SelectedRisksValidationTest {
    @Mock
    ValidationErrorFactory factory;
    @Mock
    ClassifierValueRepository classifierValueRepository;
    @InjectMocks
    private SelectedRisksValidation validation;
    @Test
    void shouldReturnErrorWhenSelectedRisksListIsEmpty() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getSelectedRisks()).thenReturn(Collections.emptyList());
        when(factory.buildError("ERROR_CODE_8")).thenReturn(new ValidationError("ERROR_CODE_8", "Selected risks list must not be empty!"));
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getErrorCode()).isEqualTo("ERROR_CODE_8");
        assertThat(validationError.get().getDescription()).isEqualTo("Selected risks list must not be empty!");
    }

    @Test
    void shouldReturnErrorWhenSelectedRisksListIsNull() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getSelectedRisks()).thenReturn(null);
        when(factory.buildError("ERROR_CODE_8")).thenReturn(new ValidationError("ERROR_CODE_8", "Selected risks list must not be empty!"));
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getErrorCode()).isEqualTo("ERROR_CODE_8");
        assertThat(validationError.get().getDescription()).isEqualTo("Selected risks list must not be empty!");
    }

    @Test
    void shouldNotReturnError() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getSelectedRisks()).thenReturn(List.of("RISK_IC_1"));
        when(classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", "RISK_IC_1"))
                .thenReturn(Optional.of(mock(ClassifierValue.class)));
        Optional<ValidationError> validationError = validation.validate(request);
        List<ValidationError> errors = validation.validateList(request);
        assertThat(validationError).isEmpty();
        assertThat(errors.isEmpty());
    }

    @Test
    void shouldReturnRiskNotFoundException() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getSelectedRisks()).thenReturn(List.of("RISK_IC_1", "RISK_IC_2"));
        when(classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", "RISK_IC_1"))
                .thenReturn(Optional.empty());
        when(classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", "RISK_IC_2"))
                .thenReturn(Optional.empty());
        Optional<ValidationError> validationError = validation.validate(request);
        List<ValidationError> errors = validation.validateList(request);
        assertThat(validationError).isEmpty();
        assertThat(errors.size()).isEqualTo(2);
    }
}
