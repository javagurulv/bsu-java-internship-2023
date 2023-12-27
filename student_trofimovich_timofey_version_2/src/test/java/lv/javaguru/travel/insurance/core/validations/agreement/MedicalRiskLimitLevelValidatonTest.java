package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicalRiskLimitLevelValidatonTest {
    @Mock private ValidationErrorFactory factory;
    @Mock
    private ClassifierValueRepository repository;
    @InjectMocks private MedicalRiskLimitLevelValidation validation;

    @Test
    void shouldReturnEmptyLimitLevelError() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getMedicalRiskLimitLevel()).thenReturn("");
        when(factory.buildError("ERROR_CODE_14")).thenReturn(new ValidationErrorDTO());
        assertThat(validation.validate(agreement)).isPresent();
    }
    @Test
    void shouldReturnNullLimitLevelError() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getMedicalRiskLimitLevel()).thenReturn(null);
        when(factory.buildError("ERROR_CODE_14")).thenReturn(new ValidationErrorDTO());
        assertThat(validation.validate(agreement)).isPresent();
    }
    @Test
    void shouldReturnLimitNotSupportedError() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getMedicalRiskLimitLevel()).thenReturn("level");
        when(repository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL",
                agreement.getMedicalRiskLimitLevel()))
                .thenReturn(Optional.empty());
        when(factory.buildError(eq("ERROR_CODE_15"), anyList())).thenReturn(new ValidationErrorDTO());
        assertThat(validation.validate(agreement)).isPresent();
    }

    @Test
    void shouldNotReturnError() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getMedicalRiskLimitLevel()).thenReturn("level");
        when(repository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL",
                agreement.getMedicalRiskLimitLevel()))
                .thenReturn(Optional.of(mock(ClassifierValue.class)));
        assertThat(validation.validate(agreement)).isEmpty();
    }


}
