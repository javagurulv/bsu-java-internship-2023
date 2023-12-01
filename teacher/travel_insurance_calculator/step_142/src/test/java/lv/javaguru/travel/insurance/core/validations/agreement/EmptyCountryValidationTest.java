package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
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
class EmptyCountryValidationTest {

    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks
    private EmptyCountryValidation validation;

    @Test
    public void shouldReturnNoErrorWhenCountryIsPresent() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getCountry()).thenReturn("SPAIN");
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement);
        assertTrue(errorOpt.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenCountryIsNull() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getCountry()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_10"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_10", "Country must be provided when TRAVEL_MEDICAL is selected"));
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_10", errorOpt.get().getErrorCode());
        assertEquals("Country must be provided when TRAVEL_MEDICAL is selected", errorOpt.get().getDescription());
    }

    @Test
    public void shouldReturnErrorWhenCountryIsEmpty() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getCountry()).thenReturn("");
        when(errorFactory.buildError("ERROR_CODE_10"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_10", "Country must be provided when TRAVEL_MEDICAL is selected"));
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_10", errorOpt.get().getErrorCode());
        assertEquals("Country must be provided when TRAVEL_MEDICAL is selected", errorOpt.get().getDescription());
    }

}