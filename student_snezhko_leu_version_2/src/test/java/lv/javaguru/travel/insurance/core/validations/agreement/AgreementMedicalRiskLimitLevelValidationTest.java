package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class AgreementMedicalRiskLimitLevelValidationTest extends AbstractAgreementValidationTest{
    private void init() {
        errorCode = "ERROR_CODE_13";
        description = "description";
        baseInit(errorCode, description, new AgreementMedicalRiskLimitLevelValidation());
    }
    @Test
    public void AgreementMedicalRiskLimitLevelExistsTest() {
        init();
        when(agreement.getMedicalRiskLimitLevel()).thenReturn("MEDICAL_RISK_LIMIT_LEVEL");
        assertEquals("", Optional.empty(), validation.validate(agreement));
    }
    @Test
    public void AgreementMedicalRiskLimitLevelIsNullTest() {
        init();
        ValidationErrorDTO error = validation.validate(agreement).get();
        assertEquals("", errorCode, error.getErrorCode());
        assertEquals("", description, error.getDescription());
        //assertTrue("", compareErrors(new ValidationErrorDTO(errorCode, description), validation.validate(agreement).get()));
    }

    private static boolean compareErrors(ValidationErrorDTO error1, ValidationErrorDTO error2) {
        return error1.getErrorCode().equals(error2.getErrorCode()) && error1.getDescription().equals(error2.getDescription());
    }
}
