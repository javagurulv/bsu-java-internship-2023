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

public class TravelRequestPersonalCodeValidationTest {
    @InjectMocks
    private TravelRequestPersonalCodeValidation personalCodeValidation;

    @Mock private ValidationErrorFactory validationErrorFactory;
    @Mock
    AgreementDTO agreementDTO;
    @Mock
    PersonDTO personDTO;
    @Test
    public void responseShouldContainErrorNullPersonalCodeTest() {
        when(personDTO.getPersonalCode()).thenReturn(null);
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError("ERROR_CODE_17")).thenReturn(validationError);
        Optional<ValidationErrorDTO> error= personalCodeValidation.validate(agreementDTO, personDTO);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }

}