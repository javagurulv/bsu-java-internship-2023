package lv.javaguru.travel.insurance.core.validations.calculate.agreement;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
public class AgreementDateFromValidationTest extends AbstractAgreementValidationTest{

    private void init() {
        errorCode = "ERROR_CODE_2";
        description = "description";
        baseInit(errorCode, description, new AgreementDateFromValidation());
    }
    @Test
    public void AgreementDateFromExistsTest() {
        init();
        when(agreement.getAgreementDateFrom()).thenReturn(Date.valueOf("2030-03-02"));
        assertEquals("", Optional.empty(), validation.validate(agreement));
    }
    @Test
    public void AgreementDateFromIsNullTest() {
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
