package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FirstNameValidationTest {
    @Mock
    private ValidationErrorFactory factory;
    @InjectMocks
    private FirstNameValidation validation;

    @Test
    void shouldReturnErrorWhenFirstNameIsNull() {
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonFirstName()).thenReturn(null);
        when(factory.buildError("ERROR_CODE_1")).thenReturn(new ValidationErrorDTO("ERROR_CODE_1", "First name must not be empty!"));
        Optional<ValidationErrorDTO> ValidationErrorDTO = validation.validate(person);
        assertThat(ValidationErrorDTO).isPresent();
        assertThat(ValidationErrorDTO.get().getErrorCode()).isEqualTo("ERROR_CODE_1");
        assertThat(ValidationErrorDTO.get().getDescription()).isEqualTo("First name must not be empty!");
    }

    @Test
    void shouldReturnErrorWhenFirstNameIsEmpty() {
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonFirstName()).thenReturn("");
        when(factory.buildError("ERROR_CODE_1")).thenReturn(new ValidationErrorDTO("ERROR_CODE_1", "First name must not be empty!"));
        Optional<ValidationErrorDTO> ValidationErrorDTO = validation.validate(person);
        assertThat(ValidationErrorDTO).isPresent();
        assertThat(ValidationErrorDTO.get().getErrorCode()).isEqualTo("ERROR_CODE_1");
        assertThat(ValidationErrorDTO.get().getDescription()).isEqualTo("First name must not be empty!");
    }

    @Test
    void shouldNotReturnError() {
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonFirstName()).thenReturn("first name");
        Optional<ValidationErrorDTO> ValidationErrorDTO = validation.validate(person);
        assertThat(ValidationErrorDTO).isEmpty();
    }
}
