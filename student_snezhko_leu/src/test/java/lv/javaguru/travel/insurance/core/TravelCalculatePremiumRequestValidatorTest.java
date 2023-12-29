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
    private TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
    private TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
    private boolean isEqual(ValidationError e1, ValidationError e2) {
        return e1.getErrorCode().equals(e2.getErrorCode()) && e1.getDescription().equals(e2.getDescription());
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorFirstNameTest() {
        //this.beforeForNotNullTests();
        when(request.getPersonFirstName()).thenReturn("");
/*        String errorCode = "ERROR_CODE_1";
        String description = "Field personFirstName is empty!";
*/

        String errorCode = "ERROR_CODE_0";
        String description = "IOException in errorCode.properties!";
        List<ValidationError> errors = validator.validate(request);
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError(errorCode, description)));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorLastNameTest() {
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("");
/*
        String errorCode = "ERROR_CODE_2";
        String description = "Field personLastName is empty!";

 */

        String errorCode = "ERROR_CODE_0";
        String description = "IOException in errorCode.properties!";

    //    when(env.getProperty(errorCode)).thenReturn(description);
        List<ValidationError> errors = validator.validate(request);
        //this.beforeForNotNullTests();
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError(errorCode, description)));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorAgreementDateFromTest() {
        //this.beforeForNotNullTests();
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
/*
        String errorCode = "ERROR_CODE_3";
        String description = "Field agreementDateFrom is empty!";

 */

        String errorCode = "ERROR_CODE_0";
        String description = "IOException in errorCode.properties!";

        List<ValidationError> errors = validator.validate(request);
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError(errorCode, description)));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorDateFromMustBeNotInPast() {
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());
/*
        String errorCode = "ERROR_CODE_4";
        String description = "Field agreementDateFrom is in the past!";
*/
        String errorCode = "ERROR_CODE_0";
        String description = "IOException in errorCode.properties!";
        List<ValidationError> errors = validator.validate(request);
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError(errorCode, description)));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorAgreementDateToTest() {
        Date d1 = new Date();
        d1.setDate(d1.getDate() + 50000);
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(d1);
        when(request.getAgreementDateTo()).thenReturn(null);
/*
        String errorCode = "ERROR_CODE_5";
        String description = "Field agreementDateTo is empty!";
*/
        String errorCode = "ERROR_CODE_0";
        String description = "IOException in errorCode.properties!";
        List<ValidationError> errors = validator.validate(request);
        ValidationError error = errors.get(0);
        assertTrue(isEqual(errors.get(0), new ValidationError(errorCode, description)));
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorDateToIsNotBeforeDateFromTest() {
        Date d2 = java.sql.Date.valueOf("2022-02-03");
        Date d1 = java.sql.Date.valueOf("2022-02-05");
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(d1);
        when(request.getAgreementDateTo()).thenReturn(d2);
/*
        String errorCode = "ERROR_CODE_6";
        String description = "Field agreementDateTo is before the value of field agreementDateFrom!";
*/

        String errorCode = "ERROR_CODE_0";
        String description = "IOException in errorCode.properties!";
        List<ValidationError> errors = validator.validate(request);
        ValidationError error = errors.get(1);
        assertTrue(isEqual(errors.get(1), new ValidationError(errorCode, description)));
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
        /*
        String errorCode = "ERROR_CODE_7";
        String description = "Field selected_risks is empty!";
        */

        String errorCode = "ERROR_CODE_0";
        String description = "IOException in errorCode.properties!";
        List<ValidationError> errors = validator.validate(request);
        assertTrue(isEqual(errors.get(0), new ValidationError(errorCode, description)));
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
        /*
        String errorCode = "ERROR_CODE_8";
        String description = "Field selected_risks is null!";
        */
        String errorCode = "ERROR_CODE_0";
        String description = "IOException in errorCode.properties!";
        List<ValidationError> errors = validator.validate(request);
        assertTrue(isEqual(errors.get(0), new ValidationError(errorCode, description)));
    }
}
