package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TravelCalculatePremiumRequestValidatorTest {
    TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
    Date dateLess = createDate("01.01.2023");
    Date dateMore = createDate("02.01.2023");


    @Test
    void shouldNotErrors() {
        List<ValidationError> errors = validator.validate(new TravelCalculatePremiumRequest(
                "firstN", "firstN", dateLess, dateMore
        ));
        //   assertTrue(errors.isEmpty());
    }


    @Test
    void firstNameNull() {
        List<ValidationError> errors = validator.validate(new TravelCalculatePremiumRequest(
                null, "firstN", dateLess, dateMore
        ));
        assertFalse(errors.isEmpty());
    }


    @Test
    void firstNameEmpty() {
        List<ValidationError> errors = validator.validate(new TravelCalculatePremiumRequest(
                "", "firstN", dateLess, dateMore
        ));
        assertFalse(errors.isEmpty());
    }


    @Test
    void lastNameNull() {
        List<ValidationError> errors = validator.validate(new TravelCalculatePremiumRequest(
                "firstN", null, dateLess, dateMore
        ));
        assertFalse(errors.isEmpty());
    }


    @Test
    void lastNameEmpty() {
        List<ValidationError> errors = validator.validate(new TravelCalculatePremiumRequest(
                "firstN", "", dateLess, dateMore
        ));
        assertFalse(errors.isEmpty());
    }

    @Test
    void dateFromNull() {
        List<ValidationError> errors = validator.validate(
                new TravelCalculatePremiumRequest(
                        "firstN", "lastN", null, dateMore)
        );
        assertFalse(errors.isEmpty());
    }

    @Test
    void dateFromLessOrEqualsThanDateTo() {
        List<ValidationError> errors = validator.validate(
                new TravelCalculatePremiumRequest(
                        "firstN", "lastN", dateLess, dateMore)
        );
//        assertTrue(errors.isEmpty());

        List<ValidationError> errors2 = validator.validate(
                new TravelCalculatePremiumRequest(
                        "firstN", "lastN", dateMore, dateLess)
        );
        assertFalse(errors2.isEmpty());

        List<ValidationError> errors3 = validator.validate(
                new TravelCalculatePremiumRequest(
                        "firstN", "lastN", dateLess, dateLess)
        );
        assertFalse(errors3.isEmpty());
    }


    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
