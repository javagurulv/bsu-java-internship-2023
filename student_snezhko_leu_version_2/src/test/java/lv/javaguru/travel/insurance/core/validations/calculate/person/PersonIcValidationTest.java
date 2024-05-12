package lv.javaguru.travel.insurance.core.validations.calculate.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

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
                .withIc(null)
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
                .withIc(UUID.fromString("12345678-1234-1234-1234-123456789101"))
                .build();

        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));
        Optional<ValidationErrorDTO> expectedError = validation.validate(person);
        assertEquals("", Optional.empty(), expectedError);
    }
}
