package lv.javaguru.travel.insurance.core.validations.calculate.premium.person;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonUUIDValidationTest {
    @Mock
    private ValidationErrorFactory factory;
    @InjectMocks
    private PersonUUIDValidation validation;
    private PersonDTO person;

    @BeforeEach
    void init() {
        person = mock(PersonDTO.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldReturnErrorWhenPersonUUIDIsNullOrEmpty(String personUUID) {
        when(person.getPersonUUID()).thenReturn(personUUID);
        when(factory.buildError("ERROR_CODE_17")).thenReturn(new ValidationErrorDTO("ERROR_CODE_17",
                "Person uuid is empty!"));
        Optional<ValidationErrorDTO> ValidationErrorDTO = validation.validate(person);
        assertThat(ValidationErrorDTO).isPresent();
        assertThat(ValidationErrorDTO.get().getErrorCode()).isEqualTo("ERROR_CODE_17");
        assertThat(ValidationErrorDTO.get().getDescription()).isEqualTo("Person uuid is empty!");
    }


    @Test
    void shouldNotReturnError() {
        when(person.getPersonUUID()).thenReturn("1212");
        Optional<ValidationErrorDTO> ValidationErrorDTO = validation.validate(person);
        assertThat(ValidationErrorDTO).isEmpty();
    }
}
