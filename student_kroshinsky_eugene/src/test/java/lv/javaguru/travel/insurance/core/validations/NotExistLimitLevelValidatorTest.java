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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class NotExistLimitLevelValidatorTest {
    @Mock private ClassifierValue classifierValue;
    @Mock private ClassifierValueRepository valueRepository;
    @Mock private TravelCalculatePremiumRequest request;
    @Mock private ValidationError validationError;
    @Mock private ValidationErrorFactory validationErrorFactory;
    @InjectMocks private NotExistLimitLevelValidator validator;
    @Test
    void injectedRepositoryAreNotNull() {
        assertNotNull(classifierValue);
        assertNotNull(valueRepository);
        assertNotNull(request);
        assertNotNull(validator);
        assertNotNull(validationError);
        assertNotNull(validationErrorFactory);
    }
    @Test
    void validateNoErrorsTest() {
        when(request.getMedicalRiskLimitLevel()).thenReturn("limit");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(valueRepository.findByClassifierTitleAndIc(anyString(), anyString())).thenReturn(Optional.of(classifierValue));

        Optional<ValidationError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }
    @Test
    void validateNoMedicalRiskTest() {
        when(request.getMedicalRiskLimitLevel()).thenReturn("limit");
        when(request.getSelectedRisks()).thenReturn(List.of());

        Optional<ValidationError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }
    @Test
    void validateLimitLevelTest() {
        when(request.getMedicalRiskLimitLevel()).thenReturn(null);
        Optional<ValidationError> result = validator.validate(request);
        assertTrue(result.isEmpty());
    }

    @Test
    void validateNotExistingLimitLevelTest() {
        when(request.getMedicalRiskLimitLevel()).thenReturn("limit");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(valueRepository.findByClassifierTitleAndIc(anyString(), anyString())).thenReturn(Optional.empty());
        when(validationErrorFactory.buildError(anyString(), anyList())).thenReturn(validationError);
        Optional<ValidationError> result = validator.validate(request);
        assertTrue(result.isPresent());
        assertEquals(validationError, result.get());
    }
}