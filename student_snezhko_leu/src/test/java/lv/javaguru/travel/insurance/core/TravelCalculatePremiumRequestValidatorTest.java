package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.InsurancePremiumRisk;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.validation.ValidationErrorsUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.*;

import java.util.Date;
import java.util.List;
import java.sql.*;

public class TravelCalculatePremiumRequestValidatorTest {
    @Mock
    Environment env;
    private TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
    private TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
    private boolean isEqual(ValidationError e1, ValidationError e2) {
        return e1.getErrorCode().equals(e2.getErrorCode()) && e1.getDescription().equals(e2.getDescription());
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorFirstNameTest() {
        //this.beforeForNotNullTests();
        when(request.getPersonFirstName()).thenReturn("");
        List<ValidationError> errors = validator.validate(request);
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError("ERROR_CODE_1", "Field personFirstName is empty!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorLastNameTest() {
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("");
        List<ValidationError> errors = validator.validate(request);
        //this.beforeForNotNullTests();
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError("ERROR_CODE_2", "Field personLastName is empty!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorAgreementDateFromTest() {
        //this.beforeForNotNullTests();
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        List<ValidationError> errors = validator.validate(request);
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError("ERROR_CODE_3", "Field agreementDateFrom is empty!")));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorDateFromMustBeNotInPast() {
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());

        List<ValidationError> errors = validator.validate(request);
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError("ERROR_CODE_4", "Field agreementDateFrom is in the past!")));
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
        assertTrue(isEqual(errors.get(0), new ValidationError("ERROR_CODE_5", "Field agreementDateTo is empty!")));
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
        assertTrue(isEqual(errors.get(1), new ValidationError("ERROR_CODE_6", "Field agreementDateTo is before the value of field agreementDateFrom!")));
    }

    @Test
    public void TravelCalculatePremiumRequestValidatorSelectedRisksIsNotBeEmptyTest() {
        Date d2 = java.sql.Date.valueOf("2029-02-05");
        Date d1 = java.sql.Date.valueOf("2029-02-03");
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(d1);
        when(request.getAgreementDateTo()).thenReturn(d2);
        when(request.getSelected_risks()).thenReturn(new ArrayList<String>());
        List<ValidationError> errors = validator.validate(request);
        assertTrue(isEqual(errors.get(0), new ValidationError("ERROR_CODE_7", "Field selected_risks is empty!")));
    }

    @Test
    public void TravelCalculatePremiumRequestValidatorSelectedRisksIsNotNullTest() {
        Date d2 = java.sql.Date.valueOf("2029-02-05");
        Date d1 = java.sql.Date.valueOf("2029-02-03");
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(d1);
        when(request.getAgreementDateTo()).thenReturn(d2);
        when(request.getSelected_risks()).thenReturn(null);
        List<ValidationError> errors = validator.validate(request);
        assertTrue(isEqual(errors.get(0), new ValidationError("ERROR_CODE_8", "Field selected_risks is null!")));
    }
}
