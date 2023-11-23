package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {

    @Mock TravelCalculatePremiumRequest request;

    @InjectMocks private TravelCalculatePremiumRequestValidator validator;

    @BeforeEach
    public void initMocks() {
        when(request.getPersonFirstName()).thenReturn("Name");
        when(request.getPersonLastName()).thenReturn("Surname");
        when(request.getAgreementDateFrom()).thenReturn(new Date(100L));
        when(request.getAgreementDateTo()).thenReturn(new Date(200L));
    }

    @Test
    public void returnNothingIfEverythingIsOk() {
        List<ValidationError> errors = validator.validate(request);
        ArrayList<ValidationError> expected = new ArrayList<ValidationError>();

        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorIfFirstNameIsEmpty() {
        when(request.getPersonFirstName()).thenReturn("");

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(List.of(
                new ValidationError("personFirstName", "Must not be empty!")));
        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorIfFirstNameConsistsOfSpaces() {
        when(request.getPersonFirstName()).thenReturn("     ");

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(List.of(
                new ValidationError("personFirstName", "Must not be empty!")));
        assertEquals(errors, expected);
    }

    @Test
    public void returnNothingIfFirstNameContainsSpacesAndSmthElse() {
        when(request.getPersonFirstName()).thenReturn("    Name     ");

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>();
        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorIfLastNameConsistsOfSpaces() {
        when(request.getPersonLastName()).thenReturn("     ");

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(List.of(
                new ValidationError("personLastName", "Must not be empty!")));
        assertEquals(errors, expected);
    }

    @Test
    public void returnNothingIfLastNameContainsSpacesAndSmthElse() {
        when(request.getPersonLastName()).thenReturn("    Surname     ");

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>();
        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorIfLastNameIsEmpty() {
        when(request.getPersonLastName()).thenReturn("");

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(List.of(
                new ValidationError("personLastName", "Must not be empty!")));
        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorsIfNamesNotOk() {
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("    ");

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(Arrays.asList(
                new ValidationError("personFirstName", "Must not be empty!"),
                new ValidationError("personLastName", "Must not be empty!")
        ));
        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorIfDateFromNotOk() {
        when(request.getAgreementDateFrom()).thenReturn(null);

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(Arrays.asList(
                new ValidationError("agreementDateFrom", "Must not be empty!")
        ));
        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorIfDateToNotOk() {
        when(request.getAgreementDateTo()).thenReturn(null);

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(Arrays.asList(
                new ValidationError("agreementDateTo", "Must not be empty!")
        ));
        assertEquals(errors, expected);
    }

    @Test
    public void returnErrorsIfEverythingNotOk() {
        when(request.getPersonFirstName()).thenReturn(" ");
        when(request.getPersonLastName()).thenReturn(" ");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(null);

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
        when(request.getAgreementDateFrom()).thenReturn(new Date(200L));
        when(request.getAgreementDateTo()).thenReturn(new Date(100L));

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>(Arrays.asList(
                new ValidationError("agreementDateTo", "Must be after agreementDateFrom!")
        ));
        assertEquals(errors, expected);
    }

    @Test
    public void returnNothingIfDateFromBeforeDateTo() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(100L));
        when(request.getAgreementDateTo()).thenReturn(new Date(200L));

        List<ValidationError> errors = validator.validate(request);

        ArrayList<ValidationError> expected = new ArrayList<ValidationError>();
        assertEquals(errors, expected);
        request.setAgreementDateTo(request.getAgreementDateFrom());
        assertEquals(errors, expected);
    }
}
