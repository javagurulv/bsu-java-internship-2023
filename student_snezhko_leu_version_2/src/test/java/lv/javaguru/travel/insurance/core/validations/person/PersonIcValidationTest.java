package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;

@ExtendWith(SpringExtension.class)
public class PersonIcValidationTest extends AbstractPersonFieldValidationTest {
    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private PersonIcValidation validation;

    private PersonDTO person;

//    @Test
    public void personIcIsNull() {
        String errorCode = "ERROR_CODE_16";
        String description = "Field personIc is empty!";
        person = createPersonDTO()
                    .withIc(null)
                    .build();

        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));
        Optional<ValidationErrorDTO> expectedError = validation.validate(person);
        assertNotEquals("", Optional.empty(), expectedError);
        assertEquals("", errorCode, expectedError.get().getErrorCode());
        assertEquals("", description, expectedError.get().getDescription());
    }

//    @Test
    public void personIcIsEmpty() {
        String errorCode = "ERROR_CODE_16";
        String description = "Field personIc is empty!";
        person = createPersonDTO()
                .withIc("")
                .build();

        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));
        Optional<ValidationErrorDTO> expectedError = validation.validate(person);
        assertNotEquals("", Optional.empty(), expectedError);
        assertEquals("", errorCode, expectedError.get().getErrorCode());
        assertEquals("", description, expectedError.get().getDescription());
    }

  //  @Test
    public void personIcIsCorrect() {
        String errorCode = "ERROR_CODE_16";
        String description = "Field personIc is empty!";
        person = createPersonDTO()
                .withIc("PERSON_VALID")
                .build();

        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));
        Optional<ValidationErrorDTO> expectedError = validation.validate(person);
        assertEquals("", Optional.empty(), expectedError);
    }
}
