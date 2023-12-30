package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SelectedRisksValidationTest {
    @Mock
    private ValidationErrorFactory factory;
    @Mock
    private ClassifierValueRepository classifierValueRepository;
    @InjectMocks
    private SelectedRisksValidation validation;
    private AgreementDTO agreement;

    @BeforeEach
    void init() {
        agreement = mock(AgreementDTO.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldReturnErrorWhenSelectedRisksListIsEmptyOrNull(List<String> risks) {
        when(agreement.getSelectedRisks()).thenReturn(risks);
        when(factory.buildError("ERROR_CODE_8")).thenReturn(new ValidationErrorDTO("ERROR_CODE_8", "Selected risks list must not be empty!"));
        Optional<ValidationErrorDTO> validationError = validation.validate(agreement);
        assertThat(validationError).isPresent();
        assertThat(validationError.get().getErrorCode()).isEqualTo("ERROR_CODE_8");
        assertThat(validationError.get().getDescription()).isEqualTo("Selected risks list must not be empty!");
    }

    @Test
    void shouldNotReturnError() {
        when(agreement.getSelectedRisks()).thenReturn(List.of("RISK_IC_1"));
        when(classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", "RISK_IC_1"))
                .thenReturn(Optional.of(mock(ClassifierValue.class)));
        Optional<ValidationErrorDTO> validationError = validation.validate(agreement);
        List<ValidationErrorDTO> errors = validation.validateList(agreement);
        assertThat(validationError).isEmpty();
        assertThat(errors.isEmpty());
    }

    @Test
    void shouldReturnRiskNotFoundException() {
        when(agreement.getSelectedRisks()).thenReturn(List.of("RISK_IC_1", "RISK_IC_2"));
        when(classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", "RISK_IC_1"))
                .thenReturn(Optional.empty());
        when(classifierValueRepository
                .findByClassifierTitleAndIc("RISK_TYPE", "RISK_IC_2"))
                .thenReturn(Optional.empty());
        Optional<ValidationErrorDTO> validationError = validation.validate(agreement);
        List<ValidationErrorDTO> errors = validation.validateList(agreement);
        assertThat(validationError).isEmpty();
        assertThat(errors.size()).isEqualTo(2);
    }
}
