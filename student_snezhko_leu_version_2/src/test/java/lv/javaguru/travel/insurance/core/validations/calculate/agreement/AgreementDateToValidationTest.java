package lv.javaguru.travel.insurance.core.validations.calculate.agreement;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class AgreementDateToValidationTest extends AbstractAgreementValidationTest{

    private void init() {
        errorCode = "ERROR_CODE_4";
        description = "description";
        baseInit(errorCode, description, new AgreementDateToValidation());
    }
    @Test
    public void AgreementDateToExistsTest() {
        init();
        when(agreement.getAgreementDateTo()).thenReturn(Date.valueOf("2015-03-02"));
        assertEquals("", Optional.empty(), validation.validate(agreement));
    }
    @Test
    public void AgreementDateTOIsNullTest() {
        init();
        ValidationErrorDTO error = validation.validate(agreement).get();
        assertEquals("", errorCode, error.getErrorCode());
        assertEquals("", description, error.getDescription());
        //assertTrue("", compareErrors(new ValidationErrorDTO(errorCode, description), validation.validate(agreement).get()));
    }
}
