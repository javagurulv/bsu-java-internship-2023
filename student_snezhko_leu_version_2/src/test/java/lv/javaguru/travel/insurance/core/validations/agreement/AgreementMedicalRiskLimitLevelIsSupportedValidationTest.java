package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class AgreementMedicalRiskLimitLevelIsSupportedValidationTest extends AbstractAgreementValidationTest{
    private String mrllIc = "mrll";
    private void init() {
        super.initAll("ERROR_CODE_14",
                "description",
                new AgreementMedicalRiskLimitLevelIsSupportedValidation(),
                "MEDICAL_RISK_LIMIT_LEVEL",
                mrllIc,
                null);
    }

    @Test
    public void notExistingMedicalRiskLimitLevel() {
        init();
        when(agreement.getMedicalRiskLimitLevel()).thenReturn(mrllIc);
        Optional<ValidationErrorDTO> error = validation.validate(agreement);
        assertEquals("", errorCode, error.get().getErrorCode());
        assertEquals("", description, error.get().getDescription());
    }

    @Test
    public void correctMedicalRiskLimitLevelTest() {
        init();
        String correctMrll = "correctMrll";
        when(agreement.getMedicalRiskLimitLevel()).thenReturn(correctMrll);
        when(classifierValueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", correctMrll)).thenReturn(Optional.of(classifierValue));
        assertEquals("", Optional.empty(), validation.validate(agreement));
    }
}
