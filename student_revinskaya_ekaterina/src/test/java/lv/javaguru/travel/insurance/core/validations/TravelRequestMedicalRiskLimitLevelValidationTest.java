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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestMedicalRiskLimitLevelValidationTest {
    @InjectMocks
    private TravelRequestMedicalRiskLimitLevelValidation travelRequestMedicalRiskLimitLevelValidation;
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Mock
    private TravelCalculatePremiumRequestV1 request;

    @Test
    public void responseShouldContainErrorNullMedicalRiskLimitLevelTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn(null);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        ReflectionTestUtils.setField(travelRequestMedicalRiskLimitLevelValidation,
                "medicalRiskLimitLevelEnabled", true);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_14")).thenReturn(validationError);
        Optional<ValidationError> error = travelRequestMedicalRiskLimitLevelValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }

    @Test
    public void responseShouldContainErrorEmptyMedicalRiskLimitLevelTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn("");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        ReflectionTestUtils.setField(travelRequestMedicalRiskLimitLevelValidation,
                "medicalRiskLimitLevelEnabled", true);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_14")).thenReturn(validationError);
        Optional<ValidationError> error = travelRequestMedicalRiskLimitLevelValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }

    @Test
    public void responseShouldNotContainErrorTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn("");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        ReflectionTestUtils.setField(travelRequestMedicalRiskLimitLevelValidation,
                "medicalRiskLimitLevelEnabled", false);
        Optional<ValidationError> error = travelRequestMedicalRiskLimitLevelValidation.validate(request);
        assertTrue(error.isEmpty());
    }
}
