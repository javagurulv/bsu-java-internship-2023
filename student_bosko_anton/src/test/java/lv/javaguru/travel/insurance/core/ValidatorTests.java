package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTests {

    DateTimeService date = new DateTimeService();
    TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void FullRequestIsValid()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Anton");
        request.setPersonLastName("Bosko");
        request.setAgreementDateFrom(date.createDate("24.08.2054"));
        request.setAgreementDateTo(date.createDate("25.08.2054"));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
    @Test
    public void FullRequestIsInvalid()
    {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Anton");
        request.setPersonLastName("Bosko");
        request.setAgreementDateFrom(date.createDate("15.08.2004"));
        request.setAgreementDateTo(date.createDate("15.08.2004"));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.get(0).getField(), "agreementDateFrom");
        assertEquals(errors.get(0).getMessage(), "cannot be the past date!");
    }
}
