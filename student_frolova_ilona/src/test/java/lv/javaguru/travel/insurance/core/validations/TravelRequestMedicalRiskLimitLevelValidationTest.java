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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestMedicalRiskLimitLevelValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private MedicalRiskLimitLevelValidation validation;

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private ClassifierValueRepository repository;

    @Test
    void returnErrorIfLimitLevelEnabledAndLimitLevelIsWrong() {
        when(request.getMedicalRiskLimitLevel()).thenReturn("LEVEL_100");

        when(repository.findByClassifierTitleAndIc(eq("MEDICAL_RISK_LIMIT_LEVEL"), any())).
        thenReturn(Optional.empty());

        ValidationError expectedError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_15")).thenReturn(expectedError);

        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", true);

        Optional<ValidationError> result = validation.check(request);

        assertTrue(result.isPresent());
        assertEquals(expectedError, result.get());
    }

    @Test
    void returnNothingIfLimitLevelEnabledAndLimitLevelIsOk() {
        when(request.getMedicalRiskLimitLevel()).thenReturn("LEVEL_10000");

        ClassifierValue classifierValue = mock(ClassifierValue.class);
        when(repository.findByClassifierTitleAndIc(eq("MEDICAL_RISK_LIMIT_LEVEL"), any())).
                thenReturn(Optional.of(classifierValue));

        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", true);

        Optional<ValidationError> result = validation.check(request);

        assertTrue(result.isEmpty());
    }

    @Test
    void returnNothingIfLimitLevelNotEnabled() {
        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", false);
        Optional<ValidationError> result = validation.check(request);
        assertTrue(result.isEmpty());
    }
}