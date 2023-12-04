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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestBirthdayValidationTest {
    @InjectMocks
    TravelRequestBirthdayValidation birthdayValidation;
    @Mock
    AgreementDTO agreementDTO;
    @Mock
    PersonDTO personDTO;
    @Mock
    ValidationErrorFactory validationErrorFactory;
    @Test
    public void responseShouldContainsErrorBirthdayInFutureTest(){
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(personDTO.getPersonBirthDate()).thenReturn(null);
        when(validationErrorFactory.buildError("ERROR_CODE_12")).thenReturn(validationError);
        Optional<ValidationErrorDTO> error= birthdayValidation.validate(agreementDTO, personDTO);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
}
