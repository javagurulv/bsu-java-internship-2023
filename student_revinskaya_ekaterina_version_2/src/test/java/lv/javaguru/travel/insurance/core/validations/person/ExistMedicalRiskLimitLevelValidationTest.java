package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExistMedicalRiskLimitLevelValidationTest {
    @InjectMocks
    private ExistMedicalRiskLimitLevelValidation medicalRiskLimitLevelValidation;
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Mock
    private ClassifierValueRepository classifierValueRepository;
    @Mock
    private AgreementDTO agreementDTO;
    @Mock
    private PersonDTO personDTO;
    @Test
    public void containErrorNotExistMedRiskLimitLevelWithEnableTest(){
        ReflectionTestUtils.setField(medicalRiskLimitLevelValidation, "medicalRiskLimitLevelEnabled",
                true);
        when(personDTO.getMedicalRiskLimitLevel()).thenReturn("FAKE");
        when(agreementDTO.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(classifierValueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL","FAKE"))
                .thenReturn(Optional.empty());
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_15"),anyList())).thenReturn(validationError);
        Optional<ValidationErrorDTO> error = medicalRiskLimitLevelValidation.validate(agreementDTO, personDTO);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
    @Test
    public void containErrorNotExistMedRiskLimitLevelWithNotEnableTest(){
        ReflectionTestUtils.setField(medicalRiskLimitLevelValidation, "medicalRiskLimitLevelEnabled",
                true);
        when(personDTO.getMedicalRiskLimitLevel()).thenReturn("FAKE");
        when(agreementDTO.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(classifierValueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL","FAKE"))
                .thenReturn(Optional.empty());
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_15"),anyList())).thenReturn(validationError);
        Optional<ValidationErrorDTO> error = medicalRiskLimitLevelValidation.validate(agreementDTO, personDTO);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
}
