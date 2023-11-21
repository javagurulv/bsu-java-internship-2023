package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelCalculatePremiumRequestValidatorTest {

    private final TravelCalculatePremiumRequestValidator validator;
    @Mock
    private final DateTimeService dateTimeService;

    TravelCalculatePremiumRequestValidatorTest() {
        validator = new TravelCalculatePremiumRequestValidator();
        dateTimeService = new DateTimeService();
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
                "", "Name", new Date(12L), new Date(1212L)
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

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(Arrays.asList(
                new ValidationError("personFirstName", "Must not be empty!"),
                new ValidationError("personLastName", "Must not be empty!"),
                new ValidationError("agreementDateFrom", "Must not be empty!"),
                new ValidationError("agreementDateTo", "Must not be empty!")
        ));

        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorIfDateFromAfterDateTo() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Ilona",
                "Frolova",
                dateTimeService.createDate("12.04.2018"),
                dateTimeService.createDate("11.04.2018")
        );

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(Arrays.asList(
                new ValidationError("agreementDateTo", "Must be after agreementDateFrom!")
        ));

        assertEquals(errors, expected);
    }

    @Test
    public void returnNothingIfDateFromBeforeDateTo() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "Ilona",
                "Frolova",
                dateTimeService.createDate("12.04.2018"),
                dateTimeService.createDate("13.04.2018")
        );

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>();

        assertEquals(errors, expected);

        request.setAgreementDateTo(request.getAgreementDateFrom());

        assertEquals(errors, expected);
    }
}
