package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }
}