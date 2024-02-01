package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.validations.AgreementDateFromLessThanAgreementDateToValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static lv.javaguru.travel.insurance.core.DateFunctions.createDateFromString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AgreementDateFromLessThanAgreementDateToValidatorTest {
    AgreementDateFromLessThanAgreementDateToValidator validator = new AgreementDateFromLessThanAgreementDateToValidator();

    @Test
    public void shouldReturnErrorIfAgreementDateFromAfterAgreementDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDateFromString("12.12.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDateFromString("12.12.2024"));
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getErrorCode(), "agreementDateFrom");
        assertEquals(errorOptional.get().getDescription(), "Must be less than agreementDateTo");
    }

    @Test
    public void shouldReturnErrorIfAgreementDateFromIsEqualToAgreementDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDateFromString("12.12.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDateFromString("12.12.2024"));
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getErrorCode(), "agreementDateFrom");
        assertEquals(errorOptional.get().getDescription(), "Must be less than agreementDateTo");
    }
}
