package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.rest.TravelRequestValidation;
import lv.javaguru.travel.insurance.rest.validation.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import java.util.List;

public class TravelCalculatePremiumRequestV1ValidatorTest {
    @InjectMocks
    private TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
    private TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

    @Test
    public void TravelCalculatePremiumRequestValidatorOneErrorTest() {
        //this.beforeForNotNullTests();
        when(request.getPersonFirstName()).thenReturn("");
        TravelRequestValidation lastName = mock(TravelRequestLastNameValidation.class);
        when(lastName.validate(request)).thenReturn(Optional.empty());

        TravelRequestValidation dateFrom = mock(TravelRequestValidation.class);
        when(dateFrom.validate(request)).thenReturn(Optional.empty());

        TravelRequestValidation dateTo = mock(TravelRequestDateToValidation.class);
        when(dateTo.validate(request)).thenReturn(Optional.empty());

        TravelRequestValidation selectRisks = mock(TravelRequestRisksIsNotNullValidation.class);
        when(selectRisks.validate(request)).thenReturn(Optional.empty());

        TravelRequestValidation firstName = mock(TravelRequestFirstNameValidation.class);
        when(firstName.validate(request)).thenReturn(Optional.of(new ValidationError("ERROR_CODE_1", "Field personFirstName is empty!")));

        String errorCode = "ERROR_CODE_1";
        String description = "Field personFirstName is empty!";

        List<TravelRequestValidation> valids = List.of(firstName, lastName, dateFrom, dateTo, selectRisks);

/*
        String errorCode = "ERROR_CODE_0";
        String description = "IOException in errorCode.properties!";
*/
        ReflectionTestUtils.setField(validator, "validations", valids);
        List<ValidationError> errors = validator.validate(request);
        ValidationError error = errors.get(0);
        assertEquals(errors.size(), 1);
//        assertTrue(isEqual(errors.get(0), new ValidationError(errorCode, description)));
    }

    @Test
    public void TravelCalculatePremiumRequestValidatorTwoErrorsTest() {
        //this.beforeForNotNullTests();
        when(request.getPersonFirstName()).thenReturn("");
        TravelRequestValidation lastName = mock(TravelRequestLastNameValidation.class);
        when(lastName.validate(request)).thenReturn(Optional.of(new ValidationError("ERROR_CODE_2", "Field personLastName is empty!")));

        TravelRequestValidation dateFrom = mock(TravelRequestValidation.class);
        when(dateFrom.validate(request)).thenReturn(Optional.empty());

        TravelRequestValidation dateTo = mock(TravelRequestDateToValidation.class);
        when(dateTo.validate(request)).thenReturn(Optional.empty());

        TravelRequestValidation selectRisks = mock(TravelRequestRisksIsNotNullValidation.class);
        when(selectRisks.validate(request)).thenReturn(Optional.empty());

        TravelRequestValidation firstName = mock(TravelRequestFirstNameValidation.class);
        when(firstName.validate(request)).thenReturn(Optional.of(new ValidationError("ERROR_CODE_1", "Field personFirstName is empty!")));

        String errorCode = "ERROR_CODE_1";
        String description = "Field personFirstName is empty!";

        List<TravelRequestValidation> valids = List.of(firstName, lastName, dateFrom, dateTo, selectRisks);

        ReflectionTestUtils.setField(validator, "validations", valids);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
    }
    /*
    @Test
    public void TravelCalculatePremiumRequestValidatorLastNameTest() {
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("");

        String errorCode = "ERROR_CODE_2";
        String description = "Field personLastName is empty!";


    //    when(env.getProperty(errorCode)).thenReturn(description);
        List<ValidationError> errors = validator.validate(request);
        //this.beforeForNotNullTests();
        ValidationError error = errors.get(0);
    //    assertTrue(isEqual(errors.get(0), new ValidationError(errorCode, description)));
    }
*/
    /*
    @Test
    public void TravelCalculatePremiumRequestValidatorAgreementDateFromTest() {
        //this.beforeForNotNullTests();
        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");

        String errorCode = "ERROR_CODE_3";
        String description = "Field agreementDateFrom is empty!";


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

        String errorCode = "ERROR_CODE_4";
        String description = "Field agreementDateFrom is in the past!";

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

        String errorCode = "ERROR_CODE_5";
        String description = "Field agreementDateTo is empty!";

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

        String errorCode = "ERROR_CODE_6";
        String description = "Field agreementDateTo is before the value of field agreementDateFrom!";

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

        String errorCode = "ERROR_CODE_7";
        String description = "Field selected_risks is empty!";

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

        String errorCode = "ERROR_CODE_8";
        String description = "Field selected_risks is null!";

        List<ValidationError> errors = validator.validate(request);
        assertTrue(isEqual(errors.get(0), new ValidationError(errorCode, description)));
    }

    */
}
