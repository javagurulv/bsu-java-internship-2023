package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.ValidationError;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumRequestValidatorTest {

    @Test
    void validateAllGood() {
        var request = new TravelCalculatePremiumRequest("John", "Snow", createDate("11.10.2004"), createDate("07.04.2020"));
        var validator=new TravelCalculatePremiumRequestValidator();
        assertTrue(validator.validate(request).isEmpty());
    }
    @Test
    void validateFirstNameIsNull() {
        var request = new TravelCalculatePremiumRequest(null, "Snow", createDate("11.10.2004"), createDate("07.04.2020"));
        var validator=new TravelCalculatePremiumRequestValidator();
        var errors=validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }
    @Test
    void validateFirstNameIsEmpty() {
        var request = new TravelCalculatePremiumRequest("", "Snow", createDate("11.10.2004"), createDate("07.04.2020"));
        var validator=new TravelCalculatePremiumRequestValidator();
        var errors=validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }
    @Test
    void validateLastNameIsNull() {
        var request = new TravelCalculatePremiumRequest("John", null, createDate("11.10.2004"), createDate("07.04.2020"));
        var validator=new TravelCalculatePremiumRequestValidator();
        var errors=validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personLastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }
    @Test
    void validateLastNameIsEmpty() {
        var request = new TravelCalculatePremiumRequest("John", "", createDate("11.10.2004"), createDate("07.04.2020"));
        var validator=new TravelCalculatePremiumRequestValidator();
        var errors=validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals("personLastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }
    @Test
    void validateAgrementDataFromIsNull() {
        var request = new TravelCalculatePremiumRequest("John", "Wick", null ,createDate("07.04.2020"));
        var validator=new TravelCalculatePremiumRequestValidator();
        var errors=validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    void validateAgreementDataToIsNull() {
        var request = new TravelCalculatePremiumRequest("John", "Wick",  createDate("11.10.2004"),null);
        var validator=new TravelCalculatePremiumRequestValidator();
        var errors=validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateTo", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }
    @Test
    void agreementDateFromIsLessAgreementDateTo() {
        var request = new TravelCalculatePremiumRequest("John", "Wick", createDate("11.10.2004"),createDate("07.04.2020"));
        var validator=new TravelCalculatePremiumRequestValidator();
        var errors=validator.validate(request);
        assertTrue(errors.isEmpty());
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}