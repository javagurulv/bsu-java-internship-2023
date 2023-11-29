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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelPersonFieldValidatorTest {

    @InjectMocks
    private TravelPersonFieldValidator validator;

    @Test
    public void shouldNotReturnErrors() {
        PersonDTO person = mock(PersonDTO.class);
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(person)).thenReturn(Optional.empty());
        when(validation1.validateList(person)).thenReturn(List.of());
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(person)).thenReturn(Optional.empty());
        when(validation2.validateList(person)).thenReturn(List.of());

        List<TravelPersonFieldValidation> personValidations = List.of(validation1, validation2);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);

        List<ValidationErrorDTO> errors = validator.validate(List.of(person));

        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnSinglePersonErrors() {
        PersonDTO person = mock(PersonDTO.class);
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(person)).thenReturn(Optional.of(new ValidationErrorDTO()));
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(person)).thenReturn(Optional.of(new ValidationErrorDTO()));
        List<TravelPersonFieldValidation> personValidations = List.of(validation1, validation2);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);
        List<ValidationErrorDTO> errors = validator.validate(List.of(person));
        assertEquals(errors.size(), 2);
    }

    @Test
    public void shouldReturnListPersonErrors() {
        PersonDTO person = mock(PersonDTO.class);
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(person)).thenReturn(Optional.empty());
        when(validation1.validateList(person)).thenReturn(List.of(new ValidationErrorDTO()));
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(person)).thenReturn(Optional.empty());
        when(validation2.validateList(person)).thenReturn(List.of(new ValidationErrorDTO()));
        List<TravelPersonFieldValidation> personValidations = List.of(validation1, validation2);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);
        List<ValidationErrorDTO> errors = validator.validate(List.of(person));
        assertEquals(errors.size(), 2);
    }

}