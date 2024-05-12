package lv.javaguru.travel.insurance.core.validations.calculate.person;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class PersonLastNameValidationTest extends AbstractPersonFieldValidationTest {
    void init() {
        super.init("ERROR_CODE_8", "description", new PersonLastNameValidation());
    }

    @Test
    public void emptyPersonFirstNameTest() {
        init();
        when(person.getPersonLastName()).thenReturn("");
        Optional<ValidationErrorDTO> error = validation.validate(person);
        assertEquals("", errorCode, error.get().getErrorCode());
        assertEquals("", description, error.get().getDescription());
    }

    @Test
    public void nullPersonLastNameTest() {
        init();
        Optional<ValidationErrorDTO> error = validation.validate(person);
        assertEquals("", errorCode, error.get().getErrorCode());
        assertEquals("", description, error.get().getDescription());
    }

    @Test
    public void correctPersonLastName() {
        init();
        when(person.getPersonLastName()).thenReturn("Last_NAME");
        assertEquals("", Optional.empty(), validation.validate(person));
    }
}
