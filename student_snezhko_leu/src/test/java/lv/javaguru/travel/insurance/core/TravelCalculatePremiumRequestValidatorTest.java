package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class TravelCalculatePremiumRequestValidatorTest {
    private List<ValidationError> errors;
    private void before() {
        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("", "", null, null);
        errors = validator.validate(request);
    }
    private boolean isEqual(ValidationError e1, ValidationError e2) {
        return e1.getField().equals(e2.getField()) && e1.getMessage().equals(e2.getMessage());
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorFirstNameTest() {
        this.before();
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError("personFirstName", "Must not be empty!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorLastNameTest() {
        this.before();
        ValidationError error = errors.get(1);
        assertTrue(isEqual(errors.get(1), new ValidationError("personLastName", "Must not be empty!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorAgreementDateFromTest() {
        this.before();
        ValidationError error = errors.get(2);
        assertTrue(isEqual(errors.get(2), new ValidationError("agreementDateFrom", "Must not be null!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorAgreementDateToTest() {
        this.before();
        ValidationError error = errors.get(3);
        assertTrue(isEqual(errors.get(3), new ValidationError("agreementDateTo", "Must not be null!")));
    }
}
