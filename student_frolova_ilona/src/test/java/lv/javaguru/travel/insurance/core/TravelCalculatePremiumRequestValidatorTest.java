package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelCalculatePremiumRequestValidatorTest {

    private final TravelCalculatePremiumRequestValidator validator;

    TravelCalculatePremiumRequestValidatorTest() {
        validator = new TravelCalculatePremiumRequestValidator();
    }

    @Test
    public void returnErrorIfFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "",
                "Frolova",
                new Date(),
                new Date()
        );

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(List.of(
                new ValidationError("personFirstName", "Must not be empty!")));

        assertEquals(errors, expected);
    }

    @Test
    public void returnNothingIfFirstNameIsOk() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Ilona",
                "Frolova",
                new Date(),
                new Date()
        );

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>();

        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorIfFirstNameConsistsOfSpaces() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "   ",
                "Frolova",
                new Date(),
                new Date()
        );

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(List.of(
                new ValidationError("personFirstName", "Must not be empty!")));

        assertEquals(errors, expected);
    }
}
