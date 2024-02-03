package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.*;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TravelCalculatePremiumRequestValidatorTest {
    List<TravelRequestValidation> validations = new ArrayList<>(List.of(new AgreementDateToValidation(),
            new AgreementDateFromValidation(),
            new PersonFirstNameValidation(),
            new PersonLastNameValidation(),
            new DateFromInFutureValidation(),
            new DateToInFutureValidation(),
            new DateFromLessThanDateToValidation()));
    private TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator(validations);

    @Test
    public void TravelCalculatePremiumRequestValidatorTest1() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest(null, null, null, null);
        List<ValidationError> errorsList = requestValidator.validate(premiumRequest);
        assertEquals(errorsList.size(), 4);

        assertEquals(errorsList.get(2).getField(), "personFirstName");
        assertEquals(errorsList.get(2).getMessage(), "Must not be empty!");

        assertEquals(errorsList.get(3).getField(), "personLastName");
        assertEquals(errorsList.get(3).getMessage(), "Must not be empty!");

        assertEquals(errorsList.get(1).getField(), "agreementDateFrom");
        assertEquals(errorsList.get(1).getMessage(), "Must not be empty!");

        assertEquals(errorsList.get(0).getField(), "agreementDateTo");
        assertEquals(errorsList.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void TravelCalculatePremiumRequestValidatorTest2() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest("", "", new Date(1999, 11, 19), new Date(1999, 11, 20));
        List<ValidationError> errorsList = requestValidator.validate(premiumRequest);
        assertEquals(errorsList.size(), 2);

        assertEquals(errorsList.get(0).getField(), "personFirstName");
        assertEquals(errorsList.get(0).getMessage(), "Must not be empty!");

        assertEquals(errorsList.get(1).getField(), "personLastName");
        assertEquals(errorsList.get(1).getMessage(), "Must not be empty!");
    }

    @Test
    public void TravelCalculatePremiumRequestValidatorTest3() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest("Balbes", "Balbesov", new Date(2022, 12, 19), new Date(2022, 11, 20));
        List<ValidationError> errorsList = requestValidator.validate(premiumRequest);
        assertEquals(errorsList.size(), 1);

        assertEquals(errorsList.get(0).getField(), "agreementDateFrom");
        assertEquals(errorsList.get(0).getMessage(), "AgreementDateFrom should be less than agreementDateTo!");
    }


    @Test
    public void TravelCalculatePremiumRequestValidatorTest4() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest("Roman", "Shiyanov", new Date(), new Date());
        List<ValidationError> errorsList = requestValidator.validate(premiumRequest);
        assertEquals(errorsList.size(), 1);
    }

    @Test
    public void TravelCalculatePremiumRequestValidatorTest5() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest("Roman", "Shiyanov", new Date(2023, 11, 19), new Date(2023, 11, 19));
        List<ValidationError> errorsList = requestValidator.validate(premiumRequest);
        assertEquals(errorsList.size(), 1);
    }

    @Test
    public void TravelCalculatePremiumRequestValidatorTest6() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest("Roman", "Shiyanov", new Date(2023, 10, 19), new Date(2023, 11, 19));
        List<ValidationError> errorsList = requestValidator.validate(premiumRequest);
        assertTrue(errorsList.isEmpty());
    }
}
