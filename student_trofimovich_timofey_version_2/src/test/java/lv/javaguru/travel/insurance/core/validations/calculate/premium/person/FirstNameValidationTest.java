package lv.javaguru.travel.insurance.core.validations.calculate.premium.person;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
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
public class FirstNameValidationTest {
    @Mock
    private ValidationErrorFactory factory;
    @InjectMocks
    private FirstNameValidation validation;
    private PersonDTO person;
    private AgreementDTO agreement;

    @BeforeEach
    void init() {
        person = mock(PersonDTO.class);
        agreement = mock(AgreementDTO.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldReturnErrorWhenFirstNameIsNullOrEmpty(String firstName) {
        when(person.getPersonFirstName()).thenReturn(firstName);
        when(factory.buildError("ERROR_CODE_1")).thenReturn(new ValidationErrorDTO("ERROR_CODE_1", "First name must not be empty!"));
        Optional<ValidationErrorDTO> ValidationErrorDTO = validation.validate(person, agreement);
        assertThat(ValidationErrorDTO).isPresent();
        assertThat(ValidationErrorDTO.get().getErrorCode()).isEqualTo("ERROR_CODE_1");
        assertThat(ValidationErrorDTO.get().getDescription()).isEqualTo("First name must not be empty!");
    }


    @Test
    void shouldNotReturnError() {
        when(person.getPersonFirstName()).thenReturn("first name");
        Optional<ValidationErrorDTO> ValidationErrorDTO = validation.validate(person, agreement);
        assertThat(ValidationErrorDTO).isEmpty();
    }
}
