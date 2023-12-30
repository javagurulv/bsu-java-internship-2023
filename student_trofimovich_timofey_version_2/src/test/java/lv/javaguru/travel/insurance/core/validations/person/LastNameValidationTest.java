package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
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
public class LastNameValidationTest {
    @Mock
    private ValidationErrorFactory factory;
    @InjectMocks
    private LastNameValidation validation = new LastNameValidation();
    private PersonDTO person;

    @BeforeEach
    void init() {
        person = mock(PersonDTO.class);
    }

    @Test
    void shouldReturnErrorWhenLastNameIsNull() {
        when(person.getPersonLastName()).thenReturn(null);
        when(factory.buildError("ERROR_CODE_2")).thenReturn(new ValidationErrorDTO("ERROR_CODE_2", "Last name must not be empty!"));
        Optional<ValidationErrorDTO> ValidationErrorDTO = validation.validate(person);
        assertThat(ValidationErrorDTO).isPresent();
        assertThat(ValidationErrorDTO.get().getErrorCode()).isEqualTo("ERROR_CODE_2");
        assertThat(ValidationErrorDTO.get().getDescription()).isEqualTo("Last name must not be empty!");
    }

    @Test
    void shouldReturnErrorWhenLastNameIsEmpty() {
        when(person.getPersonLastName()).thenReturn("");
        when(factory.buildError("ERROR_CODE_2")).thenReturn(new ValidationErrorDTO("ERROR_CODE_2", "Last name must not be empty!"));
        Optional<ValidationErrorDTO> ValidationErrorDTO = validation.validate(person);
        assertThat(ValidationErrorDTO).isPresent();
        assertThat(ValidationErrorDTO.get().getErrorCode()).isEqualTo("ERROR_CODE_2");
        assertThat(ValidationErrorDTO.get().getDescription()).isEqualTo("Last name must not be empty!");
    }

    @Test
    void shouldNotReturnError() {
        when(person.getPersonLastName()).thenReturn("last name");
        Optional<ValidationErrorDTO> ValidationErrorDTO = validation.validate(person);
        assertThat(ValidationErrorDTO).isEmpty();
    }
}
