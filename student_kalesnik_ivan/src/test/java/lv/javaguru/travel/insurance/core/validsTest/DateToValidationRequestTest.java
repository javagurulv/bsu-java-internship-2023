package lv.javaguru.travel.insurance.core.validsTest;

import lv.javaguru.travel.insurance.core.valids.DateToValidationRequest;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateToValidationRequestTest {

    private DateToValidationRequest validation;

    @BeforeEach
    public void setUp() {
        validation = new DateToValidationRequest();
    }

    @Test
    public void shouldReturnErrorWhenDateToIsNull() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(null);

        Optional<ValidationError> result = validation.execute(request);

        assertEquals("Must not be empty!", result.get().getMessage());
        assertEquals("agreementDateTo", result.get().getField());
    }

    @Test
    public void shouldReturnEmptyWhenDateToIsNotNull() {
        Date dateTo = new Date();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(dateTo);

        Optional<ValidationError> result = validation.execute(request);

        assertEquals(Optional.empty(), result);
    }
}

