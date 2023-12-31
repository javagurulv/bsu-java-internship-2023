package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TravelCalculatePremiumRequestValidatorTest {
    @Test
    public void shouldBeNormal() throws ParseException {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ivan", "Filon", new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2005"), new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2005"));
        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
        assertTrue(validator.validate(request).isEmpty());
    }

    @Test
    public void shouldBeEmptyLastName() throws ParseException {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ivan", "", new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2005"), new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2005"));
        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
        assertEquals(validator.validate(request).get(0), new ValidationError("personLastName", "Must not be empty!"));
        assertEquals(validator.validate(request).size(), 1);
    }

    @Test
    public void shouldBeEmptyFirstName() throws ParseException {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("", "Filon", new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2005"), new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2005"));
        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
        assertEquals(validator.validate(request).get(0), new ValidationError("personFirstName", "Must not be empty!"));
        assertEquals(validator.validate(request).size(), 1);
    }

    @Test
    public void shouldBeEmptyDateFrom() throws ParseException {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ivan", "Filon", null, new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2005"));
        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
        assertEquals(validator.validate(request).get(0), new ValidationError("agreementDateFrom", "Must not be empty!"));
        assertEquals(validator.validate(request).size(), 1);
    }

    @Test
    public void shouldBeEmptyDateTo() throws ParseException {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ivan", "Filon", new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2005"), null);
        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
        assertEquals(validator.validate(request).get(0), new ValidationError("agreementDateTo", "Must not be empty!"));
        assertEquals(validator.validate(request).size(), 1);
    }

    @Test
    public void shouldBeDateFromGreaterThanDateTo() throws ParseException{
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ivan", "Filon", new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2005"), new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2005"));
        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
        assertEquals(validator.validate(request).get(0), new ValidationError("agreementDateFrom", "Must be less than agreementDateTo!"));
        assertEquals(validator.validate(request).size(), 1);
    }

    @Test
    public void shouldBeAllNull(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(null, null, null, null);
        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
        assertEquals(validator.validate(request).size(),4);
        assertEquals(validator.validate(request).get(0),new ValidationError("personFirstName", "Must not be empty!"));
        assertEquals(validator.validate(request).get(1), new ValidationError("personLastName", "Must not be empty!"));
        assertEquals(validator.validate(request).get(2), new ValidationError("agreementDateFrom", "Must not be empty!"));
        assertEquals(validator.validate(request).get(3), new ValidationError("agreementDateTo", "Must not be empty!"));
    }
}
