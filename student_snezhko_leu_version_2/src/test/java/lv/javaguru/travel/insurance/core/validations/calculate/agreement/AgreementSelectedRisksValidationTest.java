package lv.javaguru.travel.insurance.core.validations.calculate.agreement;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class AgreementSelectedRisksValidationTest extends AbstractAgreementValidationTest{
    private void init() {
        errorCode = "ERROR_CODE_6";
        description = "description";
        baseInit(errorCode, description, new AgreementSelectedRisksValidation());
    }
    @Test
    public void AgreementSelectedRisksExistsTest() {
        init();
        when(agreement.getSelectedRisks()).thenReturn(List.of("SELECTED_RISK"));
        assertEquals("", Optional.empty(), validation.validate(agreement));
    }
    @Test
    public void AgreementSelectedRiskIsNullTest() {
        init();
        ValidationErrorDTO error = validation.validate(agreement).get();
        assertEquals("", errorCode, error.getErrorCode());
        assertEquals("", description, error.getDescription());
        //assertTrue("", compareErrors(new ValidationErrorDTO(errorCode, description), validation.validate(agreement).get()));
    }

    @Test
    public void AgreementSelectedRiskIsEmptyTest() {
        init();
        when(agreement.getSelectedRisks()).thenReturn(List.of());
        ValidationErrorDTO error = validation.validate(agreement).get();
        assertEquals("", errorCode, error.getErrorCode());
        assertEquals("", description, error.getDescription());
        //assertTrue("", compareErrors(new ValidationErrorDTO(errorCode, description), validation.validate(agreement).get()));
    }
}
