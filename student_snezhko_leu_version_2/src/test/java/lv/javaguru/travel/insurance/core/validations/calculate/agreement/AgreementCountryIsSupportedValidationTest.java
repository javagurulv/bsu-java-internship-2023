package lv.javaguru.travel.insurance.core.validations.calculate.agreement;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
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
        List<Placeholder> placeholders = new ArrayList<>();
        ReflectionTestUtils.setField(validation, "placeholders", placeholders);
        when(agreement.getCountry()).thenReturn(incorrectCountry);
        when(errorFactory.buildError(errorCode, placeholders)).thenReturn(new ValidationErrorDTO(errorCode, description));
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
