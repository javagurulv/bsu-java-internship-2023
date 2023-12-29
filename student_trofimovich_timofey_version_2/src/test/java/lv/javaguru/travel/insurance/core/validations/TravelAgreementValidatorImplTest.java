package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)


public class TravelAgreementValidatorImplTest {

    @InjectMocks
    private TravelAgreementValidatorImpl agreementValidatorImpl;
    
    
    @Test
    void shouldNotReturnErrors() {

        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);

        TravelAgreementFieldValidation agreementValidation1 = mock(TravelAgreementFieldValidation.class);

        when(agreementValidation1.validate(agreementDTO)).thenReturn(Optional.empty());
        when(agreementValidation1.validateList(agreementDTO)).thenReturn(List.of());

        TravelAgreementFieldValidation agreementValidation2 = mock(TravelAgreementFieldValidation.class);
        when(agreementValidation2.validate(agreementDTO)).thenReturn(Optional.empty());
        when(agreementValidation2.validateList(agreementDTO)).thenReturn(List.of());

        TravelPersonFieldValidation personVal1 = mock(TravelPersonFieldValidation.class);
        when(personVal1.validate(personDTO)).thenReturn(Optional.empty());
        when(personVal1.validateList(personDTO)).thenReturn(List.of());

        TravelPersonFieldValidation personVal2 = mock(TravelPersonFieldValidation.class);
        when(personVal2.validate(personDTO)).thenReturn(Optional.empty());
        when(personVal2.validateList(personDTO)).thenReturn(List.of());

        List<PersonDTO> persons = List.of(personDTO);
        when(agreementDTO.getPersons()).thenReturn(persons);

        List<TravelAgreementFieldValidation> agreementValidationsList = List.of(agreementValidation1, agreementValidation2);
        List<TravelPersonFieldValidation> personValidationsList = List.of(personVal1, personVal2);
        ReflectionTestUtils.setField(agreementValidatorImpl, "agreementValidations", agreementValidationsList);
        ReflectionTestUtils.setField(agreementValidatorImpl, "personValidations", personValidationsList);

        List<ValidationErrorDTO> errors = agreementValidatorImpl.validate(agreementDTO);
        assertThat(errors.size()).isEqualTo(0);
    }

    @Test
    void shouldReturnAgreementListErrors() {

        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        

        TravelAgreementFieldValidation agreementValidation1 = mock(TravelAgreementFieldValidation.class);
        when(agreementValidation1.validate(agreementDTO)).thenReturn(Optional.empty());
        when(agreementValidation1.validateList(agreementDTO)).thenReturn(List.of(mock(ValidationErrorDTO.class)));

        TravelAgreementFieldValidation agreementValidation2 = mock(TravelAgreementFieldValidation.class);
        when(agreementValidation2.validate(agreementDTO)).thenReturn(Optional.empty());
        when(agreementValidation2.validateList(agreementDTO)).thenReturn(List.of(mock(ValidationErrorDTO.class)));

        List<TravelAgreementFieldValidation> agreementValidationsList = List.of(agreementValidation1, agreementValidation2);
        ReflectionTestUtils.setField(agreementValidatorImpl, "agreementValidations", agreementValidationsList);
        ReflectionTestUtils.setField(agreementValidatorImpl, "personValidations", List.of());

        List<ValidationErrorDTO> errors = agreementValidatorImpl.validate(agreementDTO);
        assertThat(errors.size()).isGreaterThan(0);
        assertThat(errors.size()).isEqualTo(2);
    }

    @Test
    void shouldReturnListPersonErrors() {

        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);

        TravelPersonFieldValidation personVal1 = mock(TravelPersonFieldValidation.class);
        when(personVal1.validate(personDTO)).thenReturn(Optional.empty());
        when(personVal1.validateList(personDTO)).thenReturn(List.of(mock(ValidationErrorDTO.class)));

        TravelPersonFieldValidation personVal2 = mock(TravelPersonFieldValidation.class);
        when(personVal2.validate(personDTO)).thenReturn(Optional.empty());
        when(personVal2.validateList(personDTO)).thenReturn(List.of(mock(ValidationErrorDTO.class)));

        List<PersonDTO> persons = List.of(personDTO);
        when(agreementDTO.getPersons()).thenReturn(persons);

        List<TravelPersonFieldValidation> personValidationsList = List.of(personVal1, personVal2);
        ReflectionTestUtils.setField(agreementValidatorImpl, "agreementValidations", List.of());
        ReflectionTestUtils.setField(agreementValidatorImpl, "personValidations", personValidationsList);

        List<ValidationErrorDTO> errors = agreementValidatorImpl.validate(agreementDTO);
        assertThat(errors.size()).isEqualTo(2);
    }

    @Test
    void shouldReturnSingleAgreementErrors() {

        AgreementDTO agreementDTO = mock(AgreementDTO.class);

        TravelAgreementFieldValidation agreementValidation1 = mock(TravelAgreementFieldValidation.class);
        when(agreementValidation1.validate(agreementDTO)).thenReturn(Optional.of(mock(ValidationErrorDTO.class)));
        //when(agreementValidation1.validateList(agreementDTO)).thenReturn(List.of(mock(ValidationError.class)));

        TravelAgreementFieldValidation agreementValidation2 = mock(TravelAgreementFieldValidation.class);
        when(agreementValidation2.validate(agreementDTO)).thenReturn(Optional.of(mock(ValidationErrorDTO.class)));
        //when(agreementValidation2.validateList(agreementDTO)).thenReturn(List.of(mock(ValidationError.class)));



        List<TravelAgreementFieldValidation> agreementValidationsList = List.of(agreementValidation1, agreementValidation2);
        ReflectionTestUtils.setField(agreementValidatorImpl, "agreementValidations", agreementValidationsList);
        ReflectionTestUtils.setField(agreementValidatorImpl, "personValidations", List.of());

        List<ValidationErrorDTO> errors = agreementValidatorImpl.validate(agreementDTO);
        assertThat(errors.size()).isGreaterThan(0);
        assertThat(errors.size()).isEqualTo(2);
    }

    @Test
    void shouldReturnSinglePersonErrors() {

        AgreementDTO agreementDTO = mock(AgreementDTO.class);
        PersonDTO personDTO = mock(PersonDTO.class);

        TravelPersonFieldValidation personVal1 = mock(TravelPersonFieldValidation.class);
        when(personVal1.validate(personDTO)).thenReturn(Optional.of(mock(ValidationErrorDTO.class)));
        /*when(personVal1.validateList(agreementDTO)).thenReturn(List.of(mock(ValidationError.class)));*/

        TravelPersonFieldValidation personVal2 = mock(TravelPersonFieldValidation.class);
        when(personVal2.validate(personDTO)).thenReturn(Optional.of(mock(ValidationErrorDTO.class)));
        /*when(personVal2.validateList(agreementDTO)).thenReturn(List.of(mock(ValidationError.class)));*/

        List<PersonDTO> persons = List.of(personDTO);
        when(agreementDTO.getPersons()).thenReturn(persons);

        List<TravelPersonFieldValidation> personValidationsList = List.of(personVal1, personVal2);
        ReflectionTestUtils.setField(agreementValidatorImpl, "agreementValidations", List.of());
        ReflectionTestUtils.setField(agreementValidatorImpl, "personValidations", personValidationsList);

        List<ValidationErrorDTO> errors = agreementValidatorImpl.validate(agreementDTO);
        assertThat(errors.size()).isGreaterThan(0);
        assertThat(errors.size()).isEqualTo(2);
    }

}
