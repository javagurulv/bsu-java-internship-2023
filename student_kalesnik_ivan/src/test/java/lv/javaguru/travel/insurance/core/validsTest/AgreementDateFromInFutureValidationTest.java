package lv.javaguru.travel.insurance.core.validsTest;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import lv.javaguru.travel.insurance.core.valids.*;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AgreementDateFromInFutureValidationTest {

    private DateTimeService dateTimeService;
    public AgreementDateFromInFutureValidation validation;
    @BeforeEach
    public void setUp() {
        dateTimeService = Mockito.mock(DateTimeService.class);
        validation = new AgreementDateFromInFutureValidation(dateTimeService);
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsInThePast() {
        Date pastDate = new Date(System.currentTimeMillis() - 10000);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(pastDate);

        when(dateTimeService.getCurrentDateTime()).thenReturn(new Date());

        Optional<ValidationError> result = validation.execute(request);

        assertEquals("Must be in the future!", result.get().getMessage());
        assertEquals("agreementDateFrom", result.get().getField());
    }

    @Test
    public void shouldReturnEmptyWhenDateFromIsInTheFuture() {
        Date futureDate = new Date(System.currentTimeMillis() + 10000);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(futureDate);

        when(dateTimeService.getCurrentDateTime()).thenReturn(new Date());

        Optional<ValidationError> result = validation.execute(request);

        assertEquals(Optional.empty(), result);
    }
}

