package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)

public class TravelRequestPersonFirstNameValidationTest {
    @InjectMocks
    private TravelRequestPersonFirstNameValidation personFirstNameValidation;

    @Mock private ValidationErrorFactory validationErrorFactory;
    @Test
    public void responseShouldContainErrorEmptyFirstNameTest() {
        PersonDTO request = mock(PersonDTO.class);
        when(request.getPersonFirstName()).thenReturn("");
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError("ERROR_CODE_1")).thenReturn(validationError);
        Optional<ValidationErrorDTO> error= personFirstNameValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
    @Test
    public void responseShouldContainErrorNullFirstNameTest() {
        PersonDTO request = mock(PersonDTO.class);
        when(request.getPersonFirstName()).thenReturn(null);
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError("ERROR_CODE_1")).thenReturn(validationError);
        Optional<ValidationErrorDTO> error= personFirstNameValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
}