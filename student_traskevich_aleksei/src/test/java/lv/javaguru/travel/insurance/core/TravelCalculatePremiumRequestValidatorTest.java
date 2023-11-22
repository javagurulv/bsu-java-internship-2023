package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TravelCalculatePremiumRequestValidatorTest {
    TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();

    TravelCalculatePremiumRequest requestNormal = new TravelCalculatePremiumRequest(
            "firstN", "firstN", null, null
    );

    @Test
    void shouldNotErrors() {
        List<ValidationError> errors = validator.validate(requestNormal);
        assertTrue(errors.isEmpty());
    }

    TravelCalculatePremiumRequest requestFirstNameNull = new TravelCalculatePremiumRequest(
            null, "firstN", null, null
    );

    @Test
    void firstNameNull() {
        List<ValidationError> errors = validator.validate(requestFirstNameNull);
        assertTrue(!errors.isEmpty());
    }

    TravelCalculatePremiumRequest requestFirstNameEmpty = new TravelCalculatePremiumRequest(
            "", "firstN", null, null
    );

    @Test
    void firstNameEmpty() {
        List<ValidationError> errors = validator.validate(requestFirstNameEmpty);
        assertTrue(!errors.isEmpty());
    }

    TravelCalculatePremiumRequest requestLastNameNull = new TravelCalculatePremiumRequest(
            "firstN", null, null, null
    );

    @Test
    void lastNameNull() {
        List<ValidationError> errors = validator.validate(requestLastNameNull);
        assertTrue(!errors.isEmpty());
    }

    TravelCalculatePremiumRequest requestLastNameEmpty = new TravelCalculatePremiumRequest(
            "firstN", "", null, null
    );

    @Test
    void lastNameEmpty() {
        List<ValidationError> errors = validator.validate(requestLastNameEmpty);
        assertTrue(!errors.isEmpty());
    }


    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
