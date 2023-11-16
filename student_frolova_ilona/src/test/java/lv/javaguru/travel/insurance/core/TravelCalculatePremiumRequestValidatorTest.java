package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelCalculatePremiumRequestValidatorTest {

    private final TravelCalculatePremiumRequestValidator validator;

    TravelCalculatePremiumRequestValidatorTest() {
        validator = new TravelCalculatePremiumRequestValidator();
    }

    @Test
    public void returnNothingIfEverythingIsOk() {
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

    @Test
    public void returnNothingIfFirstNameContainsSpacesAndSmthElse() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                " Ilona ",
                "Frolova",
                new Date(),
                new Date()
        );

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>();

        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorIfLastNameConsistsOfSpaces() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Ilona",
                "   ",
                new Date(),
                new Date()
        );

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(List.of(
                new ValidationError("personLastName", "Must not be empty!")));

        assertEquals(errors, expected);
    }

    @Test
    public void returnNothingIfLastNameContainsSpacesAndSmthElse() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Ilona",
                "      Frolova   ",
                new Date(),
                new Date()
        );

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>();

        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorIfLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Ilona",
                "",
                new Date(),
                new Date()
        );

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(List.of(
                new ValidationError("personLastName", "Must not be empty!")));

        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorsIfNamesNotOk() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "",
                "   ",
                new Date(),
                new Date()
        );

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(Arrays.asList(
                new ValidationError("personFirstName", "Must not be empty!"),
                new ValidationError("personLastName", "Must not be empty!")
        ));

        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorIfDateFromNotOk() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Ilona",
                "Frolova",
                null,
                new Date()
        );

        DateTimeService service = new DateTimeService();

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(Arrays.asList(
                new ValidationError("agreementDateFrom", "Must not be empty!")
        ));

        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorIfDateToNotOk() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Ilona",
                "Frolova",
                new Date(),
                null
        );

        DateTimeService service = new DateTimeService();

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(Arrays.asList(
                new ValidationError("agreementDateTo", "Must not be empty!")
        ));

        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorsIfEverythingNotOk() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "  ",
                "  ",
                null,
                null
        );

        DateTimeService service = new DateTimeService();

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(Arrays.asList(
                new ValidationError("personFirstName", "Must not be empty!"),
                new ValidationError("personLastName", "Must not be empty!"),
                new ValidationError("agreementDateFrom", "Must not be empty!"),
                new ValidationError("agreementDateTo", "Must not be empty!")
        ));

        assertEquals(errors, expected);
    }
}
