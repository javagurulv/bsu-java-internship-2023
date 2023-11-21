package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.validators.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.validators.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TravelCalculatePremiumRequestValidatorTest {
    private final DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void shouldReturnErrorIfPersonFirstNameIsNull() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2015");
        Date agreementDateTo = dateTimeService.createDate("14.12.2022");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("    ", "Lebedev",
                agreementDateFrom, agreementDateTo);
        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(1, validationErrors.size());
        assertEquals("personFirstName", validationErrors.get(0).getField());
        assertEquals("Must not be empty!", validationErrors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorIfPersonFirstNameIsEmpty() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2015");
        Date agreementDateTo = dateTimeService.createDate("14.12.2022");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(null, "Lebedev",
                agreementDateFrom, agreementDateTo);
        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(1, validationErrors.size());
        assertEquals("personFirstName", validationErrors.get(0).getField());
        assertEquals("Must not be empty!", validationErrors.get(0).getMessage());
    }

    @Test
    public void shouldNotHaveError() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2015");
        Date agreementDateTo = dateTimeService.createDate("14.12.2022");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Denis", "Lebedev",
                agreementDateFrom, agreementDateTo);
        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertTrue(validationErrors.isEmpty());
    }
}
