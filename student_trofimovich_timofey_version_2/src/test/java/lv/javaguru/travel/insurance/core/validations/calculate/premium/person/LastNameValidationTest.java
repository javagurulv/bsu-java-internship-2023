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
public class LastNameValidationTest {
    @Mock
    private ValidationErrorFactory factory;
    @InjectMocks
    private LastNameValidation validation = new LastNameValidation();
    private PersonDTO person;
    private AgreementDTO agreement;

    @BeforeEach
    void init() {
        person = mock(PersonDTO.class);
        agreement = mock(AgreementDTO.class);
    }


    @ParameterizedTest
    @NullAndEmptySource
    void shouldReturnErrorWhenLastNameIsNullOrEmpty(String lastName) {
        when(person.getPersonLastName()).thenReturn(lastName);
        when(factory.buildError("ERROR_CODE_2")).thenReturn(new ValidationErrorDTO("ERROR_CODE_2", "Last name must not be empty!"));
        Optional<ValidationErrorDTO> ValidationErrorDTO = validation.validate(person, agreement);
        assertThat(ValidationErrorDTO).isPresent();
        assertThat(ValidationErrorDTO.get().getErrorCode()).isEqualTo("ERROR_CODE_2");
        assertThat(ValidationErrorDTO.get().getDescription()).isEqualTo("Last name must not be empty!");
    }

    @Test
    void shouldNotReturnError() {
        when(person.getPersonLastName()).thenReturn("last name");
        Optional<ValidationErrorDTO> ValidationErrorDTO = validation.validate(person, agreement);
        assertThat(ValidationErrorDTO).isEmpty();
    }
}
