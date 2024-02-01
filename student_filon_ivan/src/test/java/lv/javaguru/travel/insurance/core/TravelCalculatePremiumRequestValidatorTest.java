package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validator.TravelRequestValidation;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorTest {
    private TravelCalculatePremiumRequestValidator validator;
    @Test
    public void noErrors(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation = mock(TravelRequestValidation.class);
        when(validation.execute(request)).thenReturn(Optional.empty());
        List<TravelRequestValidation> validations = List.of(validation);
        validator = new TravelCalculatePremiumRequestValidator(validations);
        validator.validate(request);
        assertEquals(validator.validate(request).size(), 0);
    }

    @Test
    public void errors(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation = mock(TravelRequestValidation.class);
        when(validation.execute(request)).thenReturn(Optional.of(new ValidationError()));
        List<TravelRequestValidation> validations = List.of(validation);
        validator = new TravelCalculatePremiumRequestValidator(validations);
        validator.validate(request);
        assertEquals(validator.validate(request).size(), 1);
    }
//    @Test
//    public void shouldBeNormal() throws ParseException {
//        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ivan", "Filon", new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2115"), new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2115"));
//        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
//        assertTrue(validator.validate(request).isEmpty());
//    }
//
//    @Test
//    public void shouldBeEmptyLastName() throws ParseException {
//        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ivan", "", new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2115"), new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2115"));
//        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
//        assertEquals(validator.validate(request).get(0), new ValidationError("personLastName", "Must not be empty!"));
//        assertEquals(validator.validate(request).size(), 1);
//    }
//
//    @Test
//    public void shouldBeEmptyFirstName() throws ParseException {
//        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("", "Filon", new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2115"), new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2115"));
//        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
//        assertEquals(validator.validate(request).get(0), new ValidationError("personFirstName", "Must not be empty!"));
//        assertEquals(validator.validate(request).size(), 1);
//    }
//
//    @Test
//    public void shouldBeEmptyDateFrom() throws ParseException {
//        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ivan", "Filon", null, new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2005"));
//        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
//        assertEquals(validator.validate(request).get(0), new ValidationError("agreementDateFrom", "Must not be empty!"));
//        assertEquals(validator.validate(request).size(), 1);
//    }
//
//    @Test
//    public void shouldBeEmptyDateTo() throws ParseException {
//        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ivan", "Filon", new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2115"), null);
//        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
//        assertEquals(validator.validate(request).get(0), new ValidationError("agreementDateTo", "Must not be empty!"));
//        assertEquals(validator.validate(request).size(), 1);
//    }
//
//    @Test
//    public void shouldBeDateFromGreaterThanDateTo() throws ParseException{
//        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ivan", "Filon", new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2115"), new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2115"));
//        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
//        assertEquals(validator.validate(request).get(0), new ValidationError("agreementDateFrom", "Must be less than agreementDateTo!"));
//        assertEquals(validator.validate(request).size(), 1);
//    }
//
//    @Test
//    public void shouldBeDateFromLessThanCurrentDate() throws ParseException {
//        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ivan", "Filon", new SimpleDateFormat("dd.MM.yyyy").parse("31.05.2015"), new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2015"));
//        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
//        assertEquals(validator.validate(request).get(0), new ValidationError("agreementDateFrom", "Must be greater than currentDate!"));
//        assertEquals(validator.validate(request).size(), 1);
//    }
//
//    @Test
//    public void shouldBeAllNull(){
//        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(null, null, null, null);
//        TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
//        assertEquals(validator.validate(request).size(),4);
//        assertEquals(validator.validate(request).get(0),new ValidationError("personFirstName", "Must not be empty!"));
//        assertEquals(validator.validate(request).get(1), new ValidationError("personLastName", "Must not be empty!"));
//        assertEquals(validator.validate(request).get(2), new ValidationError("agreementDateFrom", "Must not be empty!"));
//        assertEquals(validator.validate(request).get(3), new ValidationError("agreementDateTo", "Must not be empty!"));
//    }
}
