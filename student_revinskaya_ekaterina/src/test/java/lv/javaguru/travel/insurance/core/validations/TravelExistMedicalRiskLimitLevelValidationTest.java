package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.MedicalRiskLimitLevelEnabledUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelExistMedicalRiskLimitLevelValidationTest {
    @InjectMocks
    TravelRequestExistMedicalRiskLimitLevelValidation medicalRiskLimitLevelValidation;
    @Mock
    ValidationErrorFactory validationErrorFactory;
    @Mock
    ClassifierValueRepository classifierValueRepository;
    @Mock
    TravelCalculatePremiumRequest request;
    @Mock
    MedicalRiskLimitLevelEnabledUtil medicalRiskLimitLevelEnabledUtil;
    @Test
    public void shouldContainErrorNotExistMedicalRiskLimitLevelTest(){
        when(request.getMedicalRiskLimitLevel()).thenReturn("FAKE");
        when(medicalRiskLimitLevelEnabledUtil.isMedicalRiskLimitLevelEnabled()).thenReturn(true);
        when(classifierValueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL","FAKE"))
                .thenReturn(Optional.empty());
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_15"),anyList())).thenReturn(validationError);
        Optional<ValidationError> error = medicalRiskLimitLevelValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
}
