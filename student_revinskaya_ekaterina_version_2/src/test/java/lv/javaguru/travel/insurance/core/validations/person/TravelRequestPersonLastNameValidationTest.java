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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

public class TravelRequestPersonLastNameValidationTest {
    @InjectMocks
    private TravelRequestPersonLastNameValidation personLastNameValidation;
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Mock
    AgreementDTO agreementDTO;
    @Mock
    PersonDTO personDTO;
    @Test
    public void responseShouldContainErrorEmptyLastNameTest() {
        when(personDTO.getPersonLastName()).thenReturn("");
        when(personDTO.getPersonalCode()).thenReturn(1323123L);
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_2"), anyList())).thenReturn(validationError);
        Optional<ValidationErrorDTO> error= personLastNameValidation.validate(agreementDTO, personDTO);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
    @Test
    public void responseShouldContainErrorNullLastNameTest() {
        when(personDTO.getPersonLastName()).thenReturn(null);
        when(personDTO.getPersonalCode()).thenReturn(1323123L);
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_2"), anyList())).thenReturn(validationError);
        Optional<ValidationErrorDTO> error= personLastNameValidation.validate(agreementDTO, personDTO);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
}
