package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTests {

    TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
    @Test
    public void FirstNameIsNull()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName(null);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "cannot be empty!");
    }
    @Test
    public void FirstNameIsEmpty()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "cannot be empty!");
    }
    @Test
    public void LastNameIsNull()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName(null);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.get(1).getField(), "personLastName");
        assertEquals(errors.get(1).getMessage(), "cannot be empty!");
    }
    @Test
    public void LastNameIsEmpty()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.get(1).getField(), "personLastName");
        assertEquals(errors.get(1).getMessage(), "cannot be empty!");
    }
    @Test
    public void FullNameIsValid()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Anton");
        request.setPersonLastName("Bosko");
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}
