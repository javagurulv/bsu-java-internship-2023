package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ListPersonFieldValidationTest {
    @InjectMocks
    private ListPersonFieldValidations listPersonFieldValidations;
    @Mock
    private AgreementDTO agreementDTO;
    @Mock
    private ValidationErrorDTO validationError;
    @Test
    public void validateWithListOfPersonSingleValidationsTest(){
        PersonDTO person = mock(PersonDTO.class);
        TravelPersonFieldValidation personValidation1 = mock(TravelPersonFieldValidation.class);
        TravelPersonFieldValidation personValidation2 = mock(TravelPersonFieldValidation.class);
        when(agreementDTO.getPersons()).thenReturn(List.of(person));
        ReflectionTestUtils.setField(listPersonFieldValidations, "personFieldValidations",
                List.of(personValidation1, personValidation2));
        when(personValidation1.validate(person)).thenReturn(Optional.of(validationError));
        when(personValidation2.validate(person)).thenReturn(Optional.of(validationError));

        assertEquals(listPersonFieldValidations.validateErrors(agreementDTO).size(), 2);

    }
    @Test
    public void validateWithListOfPersonListValidationsTest(){
        PersonDTO person = mock(PersonDTO.class);
        TravelPersonFieldValidation personValidation1 = mock(TravelPersonFieldValidation.class);
        TravelPersonFieldValidation personValidation2 = mock(TravelPersonFieldValidation.class);
        when(agreementDTO.getPersons()).thenReturn(List.of(person));
        ReflectionTestUtils.setField(listPersonFieldValidations, "personFieldValidations",
                List.of(personValidation1, personValidation2));
        when(personValidation1.validateList(person)).thenReturn(List.of(validationError));
        when(personValidation2.validateList(person)).thenReturn(List.of(validationError));

        assertEquals(listPersonFieldValidations.validateErrors(agreementDTO).size(), 2);

    }
    @Test
    public void validateWithListOfPersonsTest(){
        PersonDTO person1 = mock(PersonDTO.class);
        PersonDTO person2 = mock(PersonDTO.class);

        TravelPersonFieldValidation personValidation = mock(TravelPersonFieldValidation.class);
        when(agreementDTO.getPersons()).thenReturn(List.of(person1, person2));
        ReflectionTestUtils.setField(listPersonFieldValidations, "personFieldValidations",
                List.of(personValidation));
        when(personValidation.validateList(person1)).thenReturn(List.of(validationError));
        when(personValidation.validateList(person2)).thenReturn(List.of(validationError));
        assertEquals(listPersonFieldValidations.validateErrors(agreementDTO).size(), 2);

    }
}
