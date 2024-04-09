package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class EmptyLimitLevelValidatorTest {
    @Mock ValidationErrorFactory validationErrorFactory;
    @Mock ValidationError validationError;
    @Mock
    TravelCalculatePremiumRequestV1 request;
    @InjectMocks EmptyLimitLevelValidator validator;
    @Test
    void injectedRepositoryAreNotNull() {
        assertNotNull(validationErrorFactory);
        assertNotNull(validationError);
        assertNotNull(request);
        assertNotNull(validator);
    }
    @Test
    void validateNoErrorsTest() {
        ReflectionTestUtils.setField(validator, "medicalRiskLimitLevelEnabled", true);
        when(request.getMedicalRiskLimitLevel()).thenReturn("limit");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));

        Optional<ValidationError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }
    @Test
    void validateLimitLevelIsNotAvailableTest() {
        ReflectionTestUtils.setField(validator, "medicalRiskLimitLevelEnabled", false);
        Optional<ValidationError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }
    @Test
    void validateNoMedicalRiskTest() {
        ReflectionTestUtils.setField(validator, "medicalRiskLimitLevelEnabled", true);
        when(request.getSelectedRisks()).thenReturn(List.of());
        Optional<ValidationError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    void validateErrorTest() {
        ReflectionTestUtils.setField(validator, "medicalRiskLimitLevelEnabled", true);
        when(request.getMedicalRiskLimitLevel()).thenReturn(null);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(validationErrorFactory.createValidationError("ERROR_CODE_12")).thenReturn(validationError);
        Optional<ValidationError> result = validator.validate(request);
        assertTrue(result.isPresent());
        assertEquals(validationError, result.get());
    }
}