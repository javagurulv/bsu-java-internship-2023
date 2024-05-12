package lv.javaguru.travel.insurance.core.validations.calculate.person;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class PersonMedicalRiskLimitLevelValidationTest extends AbstractPersonFieldValidationTest {
    private void init() {
        errorCode = "ERROR_CODE_13";
        description = "description";
        init(errorCode, description, new PersonMedicalRiskLimitLevelValidation());
    }
    @Test
    public void AgreementMedicalRiskLimitLevelExistsTest() {
        init();
        when(person.getMedicalRiskLimitLevel()).thenReturn("MEDICAL_RISK_LIMIT_LEVEL");
        assertEquals("", Optional.empty(), validation.validate(person));
    }
    @Test
    public void AgreementMedicalRiskLimitLevelIsNullTest() {
        init();
        ValidationErrorDTO error = validation.validate(person).get();
        assertEquals("", errorCode, error.getErrorCode());
        assertEquals("", description, error.getDescription());
        //assertTrue("", compareErrors(new ValidationErrorDTO(errorCode, description), validation.validate(agreement).get()));
    }

    private static boolean compareErrors(ValidationErrorDTO error1, ValidationErrorDTO error2) {
        return error1.getErrorCode().equals(error2.getErrorCode()) && error1.getDescription().equals(error2.getDescription());
    }
}
