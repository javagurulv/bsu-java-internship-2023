package lv.javaguru.travel.insurance.core.validations.calculate.agreement;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class AgreementDateToInFutureValidationTest extends AbstractAgreementValidationTest{
    private void init() {
        errorCode = "ERROR_CODE_3";
        description = "description";
        baseInit(errorCode, description, new AgreementDateToInFutureValidation());
    }
    @Test
    public void AgreementDateToIsCorrectTest() {
        init();
        when(agreement.getAgreementDateTo()).thenReturn(Date.valueOf("2050-03-03"));

        assertEquals("", Optional.empty(), validation.validate(agreement));
    }
    @Test
    public void AgreementDateToInPastTest() {
        init();
        when(agreement.getAgreementDateTo()).thenReturn(Date.valueOf("2015-03-03"));

        ValidationErrorDTO error = validation.validate(agreement).get();

        assertEquals("", errorCode, error.getErrorCode());
        assertEquals("", description, error.getDescription());
    }
}
