package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.validations.AgreementDateFromValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AgreementDateFromValidatorTest {
    AgreementDateFromValidator validator = new AgreementDateFromValidator();

    @Test
    public void shouldReturnErrorIfAgreementDateFromIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "agreementDateFrom");
        assertEquals(errorOptional.get().getMessage(), "Must not be empty!");
    }
}
