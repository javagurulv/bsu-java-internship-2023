package lv.javaguru.travel.insurance.validators;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TravelCalculatePremiumRequestValidatorTest {
    private final DateTimeService dateTimeService = new DateTimeService();
    private final TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void shouldReturnErrorIfPersonFirstNameIsEmpty() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2025");
        Date agreementDateTo = dateTimeService.createDate("14.12.2032");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "    ", "personLastName",
                agreementDateFrom, agreementDateTo);

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(1, validationErrors.size());
        assertEquals("personFirstName", validationErrors.get(0).getField());
        assertEquals("Must not be empty!", validationErrors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorIfPersonFirstNameIsNull() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2025");
        Date agreementDateTo = dateTimeService.createDate("14.12.2032");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                null, "personLastName",
                agreementDateFrom, agreementDateTo);

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(1, validationErrors.size());
        assertEquals("personFirstName", validationErrors.get(0).getField());
        assertEquals("Must not be empty!", validationErrors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorIfPersonLastNameIsEmpty() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2025");
        Date agreementDateTo = dateTimeService.createDate("14.12.2032");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "personFirstName", "    ",
                agreementDateFrom, agreementDateTo);

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(1, validationErrors.size());
        assertEquals("personLastName", validationErrors.get(0).getField());
        assertEquals("Must not be empty!", validationErrors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorIfPersonLastNameIsNull() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2025");
        Date agreementDateTo = dateTimeService.createDate("14.12.2032");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "personFirstName", null,
                agreementDateFrom, agreementDateTo);

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(1, validationErrors.size());
        assertEquals("personLastName", validationErrors.get(0).getField());
        assertEquals("Must not be empty!", validationErrors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorIfAgreementDateFromIsNull() {
        Date agreementDateTo = dateTimeService.createDate("14.12.2032");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "personFirstName", "personLastName",
                null, agreementDateTo);

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(1, validationErrors.size());
        assertEquals("agreementDateFrom", validationErrors.get(0).getField());
        assertEquals("Must not be empty!", validationErrors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorIfAgreementDateFromInPast() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2015");
        Date agreementDateTo = dateTimeService.createDate("14.12.2032");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "personFirstName", "personLastName",
                agreementDateFrom, agreementDateTo);

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(1, validationErrors.size());
        assertEquals("agreementDateFrom", validationErrors.get(0).getField());
        assertEquals("Must be in the future!", validationErrors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorIfAgreementDateToIsNull() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2025");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "personFirstName", "personLastName",
                agreementDateFrom, null);

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(1, validationErrors.size());
        assertEquals("agreementDateTo", validationErrors.get(0).getField());
        assertEquals("Must not be empty!", validationErrors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorIfAgreementDateToInPast() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2025");
        Date agreementDateTo = dateTimeService.createDate("14.12.2012");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "personFirstName", "personLastName",
                agreementDateFrom, agreementDateTo);

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(2, validationErrors.size());
        assertEquals("agreementDateFrom", validationErrors.get(0).getField());
        assertEquals("Must be less than agreementDateTo!", validationErrors.get(0).getMessage());
        assertEquals("agreementDateTo", validationErrors.get(1).getField());
        assertEquals("Must be in the future!", validationErrors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorIfAgreementDateFromNotLessThenAgreementDateTo() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2025");
        Date agreementDateTo = dateTimeService.createDate("14.12.2024");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "personFirstName", "personLastName",
                agreementDateFrom, agreementDateTo);

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(1, validationErrors.size());
        assertEquals("agreementDateFrom", validationErrors.get(0).getField());
        assertEquals("Must be less than agreementDateTo!", validationErrors.get(0).getMessage());
    }

    @Test
    public void shouldNotHaveError() {
        Date agreementDateFrom = dateTimeService.createDate("30.01.2025");
        Date agreementDateTo = dateTimeService.createDate("14.12.2032");

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "personFirstName", "personLastName",
                agreementDateFrom, agreementDateTo);

        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertTrue(validationErrors.isEmpty());
    }
}
