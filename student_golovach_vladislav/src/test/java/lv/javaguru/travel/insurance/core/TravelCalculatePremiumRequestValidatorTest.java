package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TravelCalculatePremiumRequestValidatorTest {

    @Test
    void validateAllGood() {
        var request = new TravelCalculatePremiumRequest("John", "Snow", null, null);
        var validator=new TravelCalculatePremiumRequestValidator();
        assertTrue(validator.validate(request).isEmpty());
    }
    @Test
    void validateFirstNameIsNull() {
        var request = new TravelCalculatePremiumRequest(null, "Snow", null, null);
        var validator=new TravelCalculatePremiumRequestValidator();
        var errors=validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }
    @Test
    void validateFirstNameIsEmpty() {
        var request = new TravelCalculatePremiumRequest("", "Snow", null, null);
        var validator=new TravelCalculatePremiumRequestValidator();
        var errors=validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }
    @Test
    void validateLastNameIsNull() {
        var request = new TravelCalculatePremiumRequest("John", null, null, null);
        var validator=new TravelCalculatePremiumRequestValidator();
        var errors=validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personLastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }
    @Test
    void validateLastNameIsEmpty() {
        var request = new TravelCalculatePremiumRequest("John", "", null, null);
        var validator=new TravelCalculatePremiumRequestValidator();
        var errors=validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals("personLastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }
    @Test
    void validateAgrementDataFromIsNull() {
        var request = new TravelCalculatePremiumRequest("John", "Wick", null, new Date());
        var validator=new TravelCalculatePremiumRequestValidator();
        var errors=validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agrementDataFrom", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }
    @Test
    void validateAgrementDataFromIsEmpty() {
        var request = new TravelCalculatePremiumRequest("John", "Wick", new Date(), new Date());
        var validator=new TravelCalculatePremiumRequestValidator();
        var errors=validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals("agrementDataFrom", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

}