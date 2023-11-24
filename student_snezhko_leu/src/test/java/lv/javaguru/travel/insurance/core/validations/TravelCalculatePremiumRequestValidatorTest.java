package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.core.validations.RequestValidator;
//import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {
    private TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
    private TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
    private boolean isEqual(ValidationError e1, ValidationError e2) {
        return e1.getField().equals(e2.getField()) && e1.getMessage().equals(e2.getMessage());
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorFirstNameTest() {
        //this.beforeForNotNullTests();
        when(request.getPersonFirstName()).thenReturn("");
        List<ValidationError> errors = validator.validate(request);
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError("personFirstName", "Must not be empty!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorLastNameTest() {
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("");
        List<ValidationError> errors = validator.validate(request);
        //this.beforeForNotNullTests();
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError("personLastName", "Must not be empty!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorAgreementDateFromTest() {
        //this.beforeForNotNullTests();
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        List<ValidationError> errors = validator.validate(request);
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError("agreementDateFrom", "Must not be null!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorDateFromMustBeNotInPast() {
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());

        List<ValidationError> errors = validator.validate(request);
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError("agreementDateFrom", "Must not be in the past!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorAgreementDateToTest() {
        Date d1 = new Date();
        d1.setDate(d1.getDate() + 50000);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(d1);
        when(request.getAgreementDateTo()).thenReturn(null);

        List<ValidationError> errors = validator.validate(request);
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError("agreementDateTo", "Must not be null!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorDateToIsNotBeforeDateFromTest() {
        Date d2 = java.sql.Date.valueOf("2022-02-03");
        Date d1 = java.sql.Date.valueOf("2022-02-05");
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(d1);
        when(request.getAgreementDateTo()).thenReturn(d2);

        List<ValidationError> errors = validator.validate(request);
        ValidationError error = errors.get(1);
        assertTrue(isEqual(errors.get(1), new ValidationError("agreementDateTo", "Must not be before agreementDateFrom!!!")));
    }
}
