package lv.javaguru.travel.insurance.core.validations.calculate.agreement;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class AgreementSelectedRisksAreSupportedValidationTest extends AbstractAgreementValidationTest{
    private final String placeholderValue = "NotExistingRisk";
    private String risk;
    private void init() {
        super.initAll("ERROR_CODE_9",
                "description with placeholder",
                new AgreementSelectedRisksAreSupportedValidation(),
                "RISK_TYPE",
                risk,
                null);
    }

    @Test
    public void notExistingRiskTest() {
        init();
        risk = "NotExistingRisk";
        List<Placeholder> placeholders = List.of(new Placeholder("NOT_EXISTING_RISK", risk));
        ReflectionTestUtils.setField(validation, "placeholders", placeholders);
        when(errorFactory.buildError(errorCode, placeholders)).thenReturn(new ValidationErrorDTO(errorCode, description));
        when(agreement.getSelectedRisks()).thenReturn(List.of(risk));
        assertEquals("", errorCode, validation.validateList(agreement).get(0).getErrorCode());
    }



}
