package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelRequestDateFromValidationTest {
    @Test
    public void responseShouldContainErrorNullDateFromTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestAgreementDateFromValidation dateFromValidation = new TravelRequestAgreementDateFromValidation();
        when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> error= dateFromValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "agreementDateFrom");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }
}
