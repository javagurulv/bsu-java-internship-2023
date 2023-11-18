package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelRequestDateToValidationTest {
    @Test
    public void responseShouldContainErrorNullDateToTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestAgreementDateToValidation dateToValidation = new TravelRequestAgreementDateToValidation();
        when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> error= dateToValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "agreementDateTo");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }
}
