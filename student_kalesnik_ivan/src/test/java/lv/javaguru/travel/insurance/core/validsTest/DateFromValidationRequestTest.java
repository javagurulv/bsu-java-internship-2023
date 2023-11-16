package lv.javaguru.travel.insurance.core.validsTest;

import lv.javaguru.travel.insurance.core.valids.DateFromValidationRequest;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateFromValidationRequestTest {

    private DateFromValidationRequest validation;

    @BeforeEach
    public void setUp() {
        validation = new DateFromValidationRequest();
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsNull() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(null);

        Optional<ValidationError> result = validation.execute(request);

        assertEquals("Must not be empty!", result.get().getMessage());
        assertEquals("agreementDateFrom", result.get().getField());
    }

    @Test
    public void shouldReturnEmptyWhenDateFromIsNotNull() {
        Date dateFrom = new Date();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(dateFrom);

        Optional<ValidationError> result = validation.execute(request);

        assertEquals(Optional.empty(), result);
    }
}

