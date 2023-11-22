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
    Date date = createDate("01.01.2001");

    TravelCalculatePremiumRequest requestNormal = new TravelCalculatePremiumRequest(
            "firstN", "lastN", null, null
    );

    @Test
    void shouldNotErrors() {
        List<ValidationError> errors = validator.validate(requestNormal);
        assertTrue(errors.isEmpty());
    }

    TravelCalculatePremiumRequest requestFirstNameNull = new TravelCalculatePremiumRequest(
            null, "lastN", null, null
    );

    @Test
    void firstNameNull() {
        List<ValidationError> errors = validator.validate(requestFirstNameNull);
        assertTrue(!errors.isEmpty());
    }

    TravelCalculatePremiumRequest requestFirstNameEmpty = new TravelCalculatePremiumRequest(
            "", "lastN", null, null
    );

    @Test
    void firstNameEmpty() {
        List<ValidationError> errors = validator.validate(requestFirstNameEmpty);
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
