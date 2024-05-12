package lv.javaguru.travel.insurance.core.validations.calculate.agreement;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class AgreementPersonsValidationTest extends AbstractAgreementValidationTest{
    private void init() {
        errorCode = "ERROR_CODE_17";
        description = "description";
        baseInit(errorCode, description, new AgreementPersonsValidation());
    }
    PersonDTO person;
    @Test
    public void AgreementPersonsExistsTest() {
        person = PersonDTOBuilder.createPersonDTO().build();
        init();
        when(agreement.getPersons()).thenReturn(List.of(person));
        assertEquals("", Optional.empty(), validation.validate(agreement));
    }
    @Test
    public void AgreementDateTOIsNullTest() {
        init();
        ValidationErrorDTO error = validation.validate(agreement).get();
        assertEquals("", errorCode, error.getErrorCode());
        assertEquals("", description, error.getDescription());
        //assertTrue("", compareErrors(new ValidationErrorDTO(errorCode, description), validation.validate(agreement).get()));
    }
}
