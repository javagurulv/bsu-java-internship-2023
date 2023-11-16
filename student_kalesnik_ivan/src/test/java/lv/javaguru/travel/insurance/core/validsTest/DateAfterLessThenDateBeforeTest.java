package lv.javaguru.travel.insurance.core.validsTest;

import lv.javaguru.travel.insurance.core.valids.DateAfterLessThenDateBefore;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateAfterLessThenDateBeforeTest {

    private DateAfterLessThenDateBefore validation;

    @BeforeEach
    public void setUp() {
        validation = new DateAfterLessThenDateBefore();
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsEqualToDateTo() {
        Date date = new Date();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(date);
        request.setAgreementDateTo(date);

        Optional<ValidationError> result = validation.execute(request);

        assertEquals("Must be less then agreementDateTo!", result.get().getMessage());
        assertEquals("agreementDateFrom", result.get().getField());
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsAfterDateTo() {
        Date dateFrom = new Date(System.currentTimeMillis() + 10000);
        Date dateTo = new Date();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(dateFrom);
        request.setAgreementDateTo(dateTo);

        Optional<ValidationError> result = validation.execute(request);

        assertEquals("Must be less then agreementDateTo!", result.get().getMessage());
        assertEquals("agreementDateFrom", result.get().getField());
    }

    @Test
    public void shouldReturnEmptyWhenDateFromIsBeforeDateTo() {
        Date dateFrom = new Date();
        Date dateTo = new Date(System.currentTimeMillis() + 10000);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(dateFrom);
        request.setAgreementDateTo(dateTo);

        Optional<ValidationError> result = validation.execute(request);

        assertEquals(Optional.empty(), result);
    }
}

