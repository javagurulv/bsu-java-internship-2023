package lv.javaguru.travel.insurance.core.core.validations;

import lv.javaguru.travel.insurance.core.validations.AgreementDateToValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AgreementDateToValidatorTest {
    AgreementDateToValidator validator = new AgreementDateToValidator();

    @Test
    public void shouldReturnErrorIfAgreementDateToIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get().getField(), "agreementDateTo");
        assertEquals(errorOptional.get().getMessage(), "Must not be empty!");
    }
}
