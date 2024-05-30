package lv.javaguru.travel.insurance.core.validations.calculate.integration;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.core.validations.calculate.agreement.AgreementCostValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
public class AgreementCostValidationTest {
    @InjectMocks
    private AgreementCostValidation validation;

    @Mock
    private ValidationErrorFactory errorFactory;

    private AgreementDTO agreement;

    private final String errorCode = "ERROR_CODE_19";
    private final String description = "Field agreementCost must not be null!";

    @Test
    public void costIsNullTest() {
        agreement = createAgreementDTO().withCost(null).build();
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));

        Optional<ValidationErrorDTO> error = validation.validate(agreement);

        assertTrue("", error.isPresent());
        assertEquals("", errorCode, error.get().getErrorCode());
        assertEquals("",description, error.get().getDescription());
    }

    @Test
    public void costIsZeroTest() {
        agreement = createAgreementDTO().withCost(BigDecimal.ZERO).build();
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));

        Optional<ValidationErrorDTO> error = validation.validate(agreement);

        assertTrue("", error.isPresent());
        assertEquals("", errorCode, error.get().getErrorCode());
        assertEquals("",description, error.get().getDescription());
    }

    @Test
    public void costIsCorrectTest() {
        agreement = createAgreementDTO().withCost(BigDecimal.ONE).build();
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));

        Optional<ValidationErrorDTO> error = validation.validate(agreement);

        assertTrue("", error.isEmpty());
    }
}
