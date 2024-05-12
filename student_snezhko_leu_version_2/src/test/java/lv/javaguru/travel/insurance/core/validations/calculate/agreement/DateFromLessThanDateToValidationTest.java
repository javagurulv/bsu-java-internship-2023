package lv.javaguru.travel.insurance.core.validations.calculate.agreement;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class DateFromLessThanDateToValidationTest extends AbstractAgreementValidationTest{
    private void init(Date from, Date to) {
        errorCode = "ERROR_CODE_5";
        description = "description";
        baseInit(errorCode, description, new DateFromLessThanDateToValidation());
        when(agreement.getAgreementDateFrom()).thenReturn(from);
        when(agreement.getAgreementDateTo()).thenReturn(to);
    }
    @Test
    public void DateFromLessThanDateToTest() {
        init(Date.valueOf("2035-02-13"), Date.valueOf("2035-02-15"));
        assertEquals("",Optional.empty(), validation.validate(agreement));
    }
    @Test
    public void DateFromGreaterThanDateToTest() {
        init(Date.valueOf("2035-02-15"), Date.valueOf("2035-02-13"));
        when(agreement.getAgreementDateFrom()).thenReturn(Date.valueOf("2035-02-15"));
        when(agreement.getAgreementDateTo()).thenReturn(Date.valueOf("2035-02-13"));
        ValidationErrorDTO error = validation.validate(agreement).get();
        assertEquals("", errorCode, error.getErrorCode());
        assertEquals("", description, error.getDescription());
        //assertTrue("", compareErrors(new ValidationErrorDTO(errorCode, description), validation.validate(agreement).get()));
    }

}
