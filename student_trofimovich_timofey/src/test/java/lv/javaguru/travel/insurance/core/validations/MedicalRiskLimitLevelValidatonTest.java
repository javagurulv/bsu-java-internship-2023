package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
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
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn("");
        when(factory.buildError("ERROR_CODE_14")).thenReturn(new ValidationError());
        assertThat(validation.validate(request)).isPresent();
    }
    @Test
    void shouldReturnNullLimitLevelError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn(null);
        when(factory.buildError("ERROR_CODE_14")).thenReturn(new ValidationError());
        assertThat(validation.validate(request)).isPresent();
    }
    @Test
    void shouldReturnLimitNotSupportedError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn("level");
        when(repository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL",
                request.getMedicalRiskLimitLevel()))
                .thenReturn(Optional.empty());
        when(factory.buildError(eq("ERROR_CODE_15"), anyList())).thenReturn(new ValidationError());
        assertThat(validation.validate(request)).isPresent();
    }

    @Test
    void shouldNotReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn("level");
        when(repository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL",
                request.getMedicalRiskLimitLevel()))
                .thenReturn(Optional.of(mock(ClassifierValue.class)));
        assertThat(validation.validate(request)).isEmpty();
    }


}
