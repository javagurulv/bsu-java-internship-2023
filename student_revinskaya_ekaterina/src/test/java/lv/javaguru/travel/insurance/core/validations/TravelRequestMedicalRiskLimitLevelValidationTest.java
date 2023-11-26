package lv.javaguru.travel.insurance.core.validations;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestMedicalRiskLimitLevelValidationTest {
    @InjectMocks
    TravelRequestMedicalRiskLimitLevelValidation travelRequestMedicalRiskLimitLevelValidation;
    @Mock
    ValidationErrorFactory validationErrorFactory;
    @Mock
    TravelCalculatePremiumRequest request;
    @Test
    public void responseShouldContainErrorNullMedicalRiskLimitLevelTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn(null);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        ReflectionTestUtils.setField(travelRequestMedicalRiskLimitLevelValidation,
                "medicalRiskLimitLevelEnabled",true);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_14")).thenReturn(validationError);
        Optional<ValidationError> error= travelRequestMedicalRiskLimitLevelValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
    @Test
    public void responseShouldContainErrorEmptyMedicalRiskLimitLevelTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn("");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        ReflectionTestUtils.setField(travelRequestMedicalRiskLimitLevelValidation,
                "medicalRiskLimitLevelEnabled",true);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_14")).thenReturn(validationError);
        Optional<ValidationError> error= travelRequestMedicalRiskLimitLevelValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
    @Test
    public void responseShouldNotContainErrorTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn("");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        ReflectionTestUtils.setField(travelRequestMedicalRiskLimitLevelValidation,
                "medicalRiskLimitLevelEnabled",false);
        Optional<ValidationError> error= travelRequestMedicalRiskLimitLevelValidation.validate(request);
        assertTrue(error.isEmpty());
    }
}
