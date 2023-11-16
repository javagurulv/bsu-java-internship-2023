package lv.javaguru.travel.insurance.core.validsTest;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.core.valids.AgreementDateToInFutureValidation;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AgreementDateToInFutureValidationTest {

    private DateTimeService dateTimeService;
    private AgreementDateToInFutureValidation validation;

    @BeforeEach
    public void setUp() {
        dateTimeService = Mockito.mock(DateTimeService.class);
        validation = new AgreementDateToInFutureValidation(dateTimeService);
    }

    @Test
    public void shouldReturnErrorWhenDateToIsInThePast() {
        Date pastDate = new Date(System.currentTimeMillis() - 10000);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(pastDate);

        when(dateTimeService.getCurrentDateTime()).thenReturn(new Date());

        Optional<ValidationError> result = validation.execute(request);

        assertEquals("Must be in the future!", result.get().getMessage());
        assertEquals("agreementDateTo", result.get().getField());
    }

    @Test
    public void shouldReturnEmptyWhenDateToIsInTheFuture() {
        Date futureDate = new Date(System.currentTimeMillis() + 10000);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(futureDate);

        when(dateTimeService.getCurrentDateTime()).thenReturn(new Date());

        Optional<ValidationError> result = validation.execute(request);

        assertEquals(Optional.empty(), result);
    }
}
