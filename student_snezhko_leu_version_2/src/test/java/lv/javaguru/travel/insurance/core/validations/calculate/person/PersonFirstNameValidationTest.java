package lv.javaguru.travel.insurance.core.validations.calculate.person;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class PersonFirstNameValidationTest extends AbstractPersonFieldValidationTest{

    void init() {
        super.init("ERROR_CODE_7", "description", new PersonFirstNameValidation());
    }

    @Test
    public void emptyPersonFirstNameTest() {
        init();
        when(person.getPersonFirstName()).thenReturn("");
        Optional<ValidationErrorDTO> error = validation.validate(person);
        assertEquals("", errorCode, error.get().getErrorCode());
        assertEquals("", description, error.get().getDescription());
    }

    @Test
    public void nullPersonFirstNameTest() {
        init();
        Optional<ValidationErrorDTO> error = validation.validate(person);
        assertEquals("", errorCode, error.get().getErrorCode());
        assertEquals("", description, error.get().getDescription());
    }

    @Test
    public void correctPersonFirstName() {
        init();
        when(person.getPersonFirstName()).thenReturn("FIRST_NAME");
        assertEquals("", Optional.empty(), validation.validate(person));
    }
}
