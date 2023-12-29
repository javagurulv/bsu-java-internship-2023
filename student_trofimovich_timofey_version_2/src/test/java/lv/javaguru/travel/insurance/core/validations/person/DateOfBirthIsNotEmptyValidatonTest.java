package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DateOfBirthIsNotEmptyValidatonTest {
    @Mock
    ValidationErrorFactory errorFactory;
    @InjectMocks
    DateOfBirthIsNotEmptyValidaton validaton;

    @Test
    void shouldReturnErrorWhenDateOfBirthIsNull() {
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonBirthDate()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_12")).thenReturn(new ValidationErrorDTO("ERROR_CODE_12", "desc"));
        Optional<ValidationErrorDTO> ValidationErrorDTO = validaton.validate(person);
        assertThat(ValidationErrorDTO).isPresent();
        assertThat(ValidationErrorDTO.get().getErrorCode()).isEqualTo("ERROR_CODE_12");
        assertThat(ValidationErrorDTO.get().getDescription()).isEqualTo("desc");
    }


    @Test
    void shouldNotReturnError() {
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonBirthDate()).thenReturn(new Date());
        Optional<ValidationErrorDTO> ValidationErrorDTO = validaton.validate(person);
        assertThat(ValidationErrorDTO).isEmpty();
    }

}
