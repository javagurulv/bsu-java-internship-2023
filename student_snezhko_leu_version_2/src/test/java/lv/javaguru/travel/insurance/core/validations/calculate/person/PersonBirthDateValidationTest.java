package lv.javaguru.travel.insurance.core.validations.calculate.person;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class PersonBirthDateValidationTest extends AbstractPersonFieldValidationTest{
    private void init() {
        super.init("ERROR_CODE_11", "Person birth date field must not be empty!", new PersonBirthDateValidation());
    }
    
    @Test
    public void nullPersonBirthDateTest() {
        init();
        Optional<ValidationErrorDTO> error = validation.validate(person);
        assertEquals("", errorCode, error.get().getErrorCode());
        assertEquals("", description, error.get().getDescription());
    }

    @Test
    public void notNullPersonBirthDateTest() {
        init();
        when(person.getPersonBirthDate()).thenReturn(Date.valueOf("2005-02-20"));
        assertEquals("", Optional.empty(), validation.validate(person));
    }
}
