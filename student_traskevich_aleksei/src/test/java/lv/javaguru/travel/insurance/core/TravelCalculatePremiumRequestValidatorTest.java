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
    Date date = createDate("01.01.2023");


    @Test
    void shouldNotErrors() {
        List<ValidationError> errors = validator.validate(new TravelCalculatePremiumRequest(
                "firstN", "firstN", date, date
        ));
        assertTrue(errors.isEmpty());
    }


    @Test
    void firstNameNull() {
        List<ValidationError> errors = validator.validate(new TravelCalculatePremiumRequest(
                null, "firstN", date, date
        ));
        assertTrue(!errors.isEmpty());
    }


    @Test
    void firstNameEmpty() {
        List<ValidationError> errors = validator.validate(new TravelCalculatePremiumRequest(
                "", "firstN", date, date
        ));
        assertTrue(!errors.isEmpty());
    }


    @Test
    void lastNameNull() {
        List<ValidationError> errors = validator.validate(new TravelCalculatePremiumRequest(
                "firstN", null, date, date
        ));
        assertTrue(!errors.isEmpty());
    }


    @Test
    void lastNameEmpty() {
        List<ValidationError> errors = validator.validate(new TravelCalculatePremiumRequest(
                "firstN", "", date, date
        ));
        assertTrue(!errors.isEmpty());
    }

    @Test
    void dateFromEmpty() {
        List<ValidationError> errors = validator.validate(
                new TravelCalculatePremiumRequest(
                        "firstN", "lastN", null, date)
        );
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
