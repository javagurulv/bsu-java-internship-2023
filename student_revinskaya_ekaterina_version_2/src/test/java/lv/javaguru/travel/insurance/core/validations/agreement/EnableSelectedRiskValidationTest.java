package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class EnableSelectedRiskValidationTest {
    @InjectMocks
private EnableSelectedRisksValidation enableSelectedRisksValidation;
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Mock
    private AgreementDTO agreementDTO;
    @Test
    public void containErrorNotEnableRiskTest() {
        ReflectionTestUtils.setField(enableSelectedRisksValidation, "cancellationRiskEnable",
                false);
        when(agreementDTO.getSelectedRisks()).thenReturn(List.of("TRAVEL_CANCELLATION"));

        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError("ERROR_CODE_22")).thenReturn(validationError);
        Optional<ValidationErrorDTO> error = enableSelectedRisksValidation.validate(agreementDTO);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
}
