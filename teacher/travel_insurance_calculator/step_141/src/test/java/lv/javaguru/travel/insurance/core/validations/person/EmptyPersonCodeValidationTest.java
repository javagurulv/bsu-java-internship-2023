package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmptyPersonCodeValidationTest {

    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks
    private EmptyPersonCodeValidation validation;

    @Test
    public void shouldReturnErrorWhenPersonCodeIsNull() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonCode()).thenReturn(null);
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_16")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldReturnErrorWhenPersonCodeIsEmpty() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonCode()).thenReturn("");
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_16")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonCodeIsPresent() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonCode()).thenReturn("123456-12345");
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }

}