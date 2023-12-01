package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
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
public class MedicalRiskLimitLevelValidationTest {
    @InjectMocks
    private MedicalRiskLimitLevelValidation travelRequestMedicalRiskLimitLevelValidation;
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Mock
    private AgreementDTO request;

    @Test
    public void responseShouldContainErrorNullMedicalRiskLimitLevelTest() {
        AgreementDTO request = mock(AgreementDTO.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn(null);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        ReflectionTestUtils.setField(travelRequestMedicalRiskLimitLevelValidation,
                "medicalRiskLimitLevelEnabled", true);
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError("ERROR_CODE_14")).thenReturn(validationError);
        Optional<ValidationErrorDTO> error = travelRequestMedicalRiskLimitLevelValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }

    @Test
    public void responseShouldContainErrorEmptyMedicalRiskLimitLevelTest() {
        AgreementDTO request = mock(AgreementDTO.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn("");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        ReflectionTestUtils.setField(travelRequestMedicalRiskLimitLevelValidation,
                "medicalRiskLimitLevelEnabled", true);
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError("ERROR_CODE_14")).thenReturn(validationError);
        Optional<ValidationErrorDTO> error = travelRequestMedicalRiskLimitLevelValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }

    @Test
    public void responseShouldNotContainErrorTest() {
        AgreementDTO request = mock(AgreementDTO.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn("");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        ReflectionTestUtils.setField(travelRequestMedicalRiskLimitLevelValidation,
                "medicalRiskLimitLevelEnabled", false);
        Optional<ValidationErrorDTO> error = travelRequestMedicalRiskLimitLevelValidation.validate(request);
        assertTrue(error.isEmpty());
    }
}
