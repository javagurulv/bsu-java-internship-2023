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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
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
    public void shouldNotReturnErrorWhenMedicalRiskLimitLevelIsNotEnabled() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        ReflectionTestUtils.setField(validation, "limitLevelIsSet", false);
        Optional<ValidationError> validationError = validation.validate(request);
        assertThat(validationError).isEmpty();
        verifyNoInteractions(repository, factory);
    }
    @Test
    void shouldNotReturnErrorWhenTravelMedicalIsNotChosen() {
        ReflectionTestUtils.setField(validation, "limitLevelIsSet", true);
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of());
        assertThat(validation.validate(request)).isEmpty();
    }
    @Test
    void shouldReturnEmptyLimitLevelError() {
        ReflectionTestUtils.setField(validation, "limitLevelIsSet", true);
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn("");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(factory.buildError("ERROR_CODE_14")).thenReturn(new ValidationError());
        assertThat(validation.validate(request)).isPresent();
    }
    @Test
    void shouldReturnNullLimitLevelError() {
        ReflectionTestUtils.setField(validation, "limitLevelIsSet", true);
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn(null);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(factory.buildError("ERROR_CODE_14")).thenReturn(new ValidationError());
        assertThat(validation.validate(request)).isPresent();
    }
    @Test
    void shouldReturnLimitNotSupportedError() {
        ReflectionTestUtils.setField(validation, "limitLevelIsSet", true);
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn("level");
        when(repository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL",
                request.getMedicalRiskLimitLevel()))
                .thenReturn(Optional.empty());
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(factory.buildError(eq("ERROR_CODE_15"), anyList())).thenReturn(new ValidationError());
        assertThat(validation.validate(request)).isPresent();
    }

    @Test
    void shouldNotReturnError() {
        ReflectionTestUtils.setField(validation, "limitLevelIsSet", true);
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn("level");
        when(repository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL",
                request.getMedicalRiskLimitLevel()))
                .thenReturn(Optional.of(mock(ClassifierValue.class)));
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        assertThat(validation.validate(request)).isEmpty();
    }


}
