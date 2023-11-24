package lv.javaguru.travel.insurance.validators;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonLastNameValidatorTest {
    private final DateTimeService dateTimeService = new DateTimeService();
    private final TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void shouldReturnErrorIfPersonFirstNameIsEmpty() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2015");
        Date agreementDateTo = dateTimeService.createDate("14.12.2022");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "personFirstName", "    ",
                agreementDateFrom, agreementDateTo);

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(1, validationErrors.size());
        assertEquals("personLastName", validationErrors.get(0).getField());
        assertEquals("Must not be empty!", validationErrors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorIfPersonFirstNameIsNull() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2015");
        Date agreementDateTo = dateTimeService.createDate("14.12.2022");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "personFirstName", null,
                agreementDateFrom, agreementDateTo);

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(1, validationErrors.size());
        assertEquals("personLastName", validationErrors.get(0).getField());
        assertEquals("Must not be empty!", validationErrors.get(0).getMessage());
    }

    @Test
    public void shouldNotHaveError() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2015");
        Date agreementDateTo = dateTimeService.createDate("14.12.2022");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "personFirstName", "personLastName",
                agreementDateFrom, agreementDateTo);

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertTrue(validationErrors.isEmpty());
    }
}
