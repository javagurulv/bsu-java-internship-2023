package lv.javaguru.travel.insurance.core.validations;

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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestMedicalRiskLimitLevelNotNullValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private MedicalRiskLimitLevelNotNullValidation validation;

    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    void returnErrorIfLimitLevelEnabledAndLimitLevelIsNull() {
        ValidationError expectedError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_14")).thenReturn(expectedError);

        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", true);

        Optional<ValidationError> result = validation.check(request);

        assertTrue(result.isPresent());
        assertEquals(expectedError, result.get());
    }

    @Test
    void returnNothingIfLimitLevelEnabledAndLimitLevelNotNull() {
        when(request.getMedicalRiskLimitLevel()).thenReturn("LEVEL_10000");

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
