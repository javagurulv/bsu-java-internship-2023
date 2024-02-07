package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class AgreementCountryIsSupportedValidationTest extends AbstractAgreementValidationTest{
    private String incorrectCountry = "INCORRECT_COUNTRY";
    private void init() {
        initAll("ERROR_CODE_15",
                "description",
                new AgreementCountryIsSupportedValidation(),
                "COUNTRY",
                incorrectCountry,
                null
                );
    }

    @Test
    public void notExistingCountryTest() {
        init();
        when(agreement.getCountry()).thenReturn(incorrectCountry);
        Optional<ValidationErrorDTO> error = validation.validate(agreement);
        assertEquals("", errorCode, error.get().getErrorCode());
        assertEquals("", description, error.get().getDescription());
    }

    @Test
    public void correctCountry() {
        init();
        String correctCountry = "CORRECT_COUNTRY";
        when(agreement.getCountry()).thenReturn(correctCountry);
        when(classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", correctCountry)).thenReturn(Optional.of(classifierValue));
        assertEquals("", Optional.empty(), validation.validate(agreement));
    }
}
