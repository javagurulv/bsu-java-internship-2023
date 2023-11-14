package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.util.List;

public class TravelCalculatePremiumRequestValidatorTest {
    private List<ValidationError> errors;
    private void beforeForNotNullTests() {
        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("", "", null, null);
        errors = validator.validate(request);
    }
    private void beforeForDateSequenceTests() {
        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("", "", new Date(25000), new Date(24000));
        errors = validator.validate(request);
    }
    private boolean isEqual(ValidationError e1, ValidationError e2) {
        return e1.getField().equals(e2.getField()) && e1.getMessage().equals(e2.getMessage());
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorFirstNameTest() {
        this.beforeForNotNullTests();
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError("personFirstName", "Must not be empty!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorLastNameTest() {
        this.beforeForNotNullTests();
        ValidationError error = errors.get(1);
        assertTrue(isEqual(errors.get(1), new ValidationError("personLastName", "Must not be empty!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorAgreementDateFromTest() {
        this.beforeForNotNullTests();
        ValidationError error = errors.get(2);
        assertTrue(isEqual(errors.get(2), new ValidationError("agreementDateFrom", "Must not be null!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorAgreementDateToTest() {
        this.beforeForNotNullTests();
        ValidationError error = errors.get(3);
        assertTrue(isEqual(errors.get(3), new ValidationError("agreementDateTo", "Must not be null!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorDateToIsNotBeforeDateFromTest() {
        this.beforeForDateSequenceTests();
        ValidationError error = errors.get(2);
        assertTrue(isEqual(errors.get(2), new ValidationError("agreementDateTo", "Must not be before agreementDateFrom!!!")));
    }
}
