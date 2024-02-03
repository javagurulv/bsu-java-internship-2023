package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DateToValidationTest {
    private AgreementDateToValidation validation = new AgreementDateToValidation();

    @Test
    public void testWhenDateToIsNull() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> error = validation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "agreementDateTo");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void testWhenDateFromIsNotNull() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(new Date(2100, 12, 12));
        Optional<ValidationError> error = validation.validate(request);
        assertTrue(error.isEmpty());
    }
}
