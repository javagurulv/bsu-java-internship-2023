package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.core.validations.person.MedicalRiskLimitLevelValidation;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicalRiskLimitLevelValidationTest {
    @InjectMocks
    private MedicalRiskLimitLevelValidation travelRequestMedicalRiskLimitLevelValidation;
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Mock
    private AgreementDTO agreementDTO;
    @Mock
    private PersonDTO personDTO;

    @Test
    public void responseShouldContainErrorNullMedicalRiskLimitLevelTest() {
        when(personDTO.getMedicalRiskLimitLevel()).thenReturn(null);
        when(personDTO.getPersonalCode()).thenReturn("1323123");
        when(agreementDTO.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        ReflectionTestUtils.setField(travelRequestMedicalRiskLimitLevelValidation,
                "medicalRiskLimitLevelEnabled", true);
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_14"), anyList())).thenReturn(validationError);
        Optional<ValidationErrorDTO> error = travelRequestMedicalRiskLimitLevelValidation.validate(agreementDTO, personDTO);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }

    @Test
    public void responseShouldContainErrorEmptyMedicalRiskLimitLevelTest() {
        when(personDTO.getMedicalRiskLimitLevel()).thenReturn("");
        when(personDTO.getPersonalCode()).thenReturn("1323123");
        when(agreementDTO.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        ReflectionTestUtils.setField(travelRequestMedicalRiskLimitLevelValidation,
                "medicalRiskLimitLevelEnabled", true);
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_14"), anyList())).thenReturn(validationError);
        Optional<ValidationErrorDTO> error = travelRequestMedicalRiskLimitLevelValidation.validate(agreementDTO, personDTO);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }

    @Test
    public void responseShouldNotContainErrorTest() {
        when(personDTO.getMedicalRiskLimitLevel()).thenReturn("");
        when(agreementDTO.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        ReflectionTestUtils.setField(travelRequestMedicalRiskLimitLevelValidation,
                "medicalRiskLimitLevelEnabled", false);
        Optional<ValidationErrorDTO> error = travelRequestMedicalRiskLimitLevelValidation.validate(agreementDTO, personDTO);
        assertTrue(error.isEmpty());
    }
}
