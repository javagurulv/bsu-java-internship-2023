package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Date;
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
    public void AgreementDateFromIsNull()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(null);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.get(2).getField(), "agreementDateFrom");
        assertEquals(errors.get(2).getMessage(), "cannot be empty!");
    }
    @Test
    public void AgreementDateToIsNull()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(null);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.get(3).getField(), "agreementDateTo");
        assertEquals(errors.get(3).getMessage(), "cannot be empty!");
    }
    @Test
    public void FullRequestIsValid()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Anton");
        request.setPersonLastName("Bosko");
        request.setAgreementDateFrom(new Date(123123123L));
        request.setAgreementDateTo(new Date(123123123L));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}
