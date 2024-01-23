package lv.javaguru.travel.insurance.core.validations.calculate.premium;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.calculate.premium.person.TravelPersonFieldValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPersonFieldValidatorTest {
    @InjectMocks
    private TravelPersonFieldValidator personFieldValidator;

    private TravelPersonFieldValidation personFieldValidation1;
    private TravelPersonFieldValidation personFieldValidation2;

    private PersonDTO person1;
    private PersonDTO person2;

    private AgreementDTO agreement;

    @BeforeEach
    void init() {
        personFieldValidation1 = mock(TravelPersonFieldValidation.class);
        personFieldValidation2 = mock(TravelPersonFieldValidation.class);
        person1 = mock(PersonDTO.class);
        person2 = mock(PersonDTO.class);
        agreement = mock(AgreementDTO.class);
    }

    @Test
    void shouldNotReturnErrors() {

        when(personFieldValidation1.validate(any(PersonDTO.class))).thenReturn(Optional.empty());
        when(personFieldValidation1.validateList(any())).thenReturn(List.of());


        List<PersonDTO> persons = List.of(person1, person2);
        when(agreement.getPersons()).thenReturn(persons);

        List<TravelPersonFieldValidation> personValidationsList = List.of(personFieldValidation1);
        ReflectionTestUtils.setField(personFieldValidator, "personValidations", personValidationsList);

        List<ValidationErrorDTO> errors = personFieldValidator.validate(agreement);
        assertThat(errors.size()).isEqualTo(0);
    }

    @Test
    void shouldReturnListPersonErrors() {


        when(personFieldValidation1.validate(any(PersonDTO.class))).thenReturn(Optional.empty());
        when(personFieldValidation1.validateList(any(PersonDTO.class))).thenReturn(List.of(mock(ValidationErrorDTO.class),
                mock(ValidationErrorDTO.class)));


        when(personFieldValidation2.validate(any(PersonDTO.class))).thenReturn(Optional.empty());
        when(personFieldValidation2.validateList(any(PersonDTO.class))).thenReturn(List.of(mock(ValidationErrorDTO.class),
                mock(ValidationErrorDTO.class)));

        List<PersonDTO> persons = List.of(person1, person2);
        when(agreement.getPersons()).thenReturn(persons);

        List<TravelPersonFieldValidation> personValidationsList = List.of(personFieldValidation1, personFieldValidation2);
        ReflectionTestUtils.setField(personFieldValidator, "personValidations", personValidationsList);

        List<ValidationErrorDTO> errors = personFieldValidator.validate(agreement);
        assertThat(errors.size()).isEqualTo(8);
    }

    @Test
    void shouldReturnSinglePersonErrors() {


        when(personFieldValidation1.validate(any(PersonDTO.class))).thenReturn(Optional.of(mock(ValidationErrorDTO.class)));
        when(personFieldValidation2.validate(any(PersonDTO.class))).thenReturn(Optional.of(mock(ValidationErrorDTO.class)));

        List<PersonDTO> persons = List.of(person1, person2);
        when(agreement.getPersons()).thenReturn(persons);

        List<TravelPersonFieldValidation> personValidationsList = List.of(personFieldValidation1, personFieldValidation2);
        ReflectionTestUtils.setField(personFieldValidator, "personValidations", personValidationsList);

        List<ValidationErrorDTO> errors = personFieldValidator.validate(agreement);
        assertThat(errors.size()).isGreaterThan(0);
        assertThat(errors.size()).isEqualTo(4);
    }
}
