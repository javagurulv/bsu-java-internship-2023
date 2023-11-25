package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TravelRequestFirstNameValidationTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @InjectMocks
    private TravelRequestFirstNameValidation validation;

    @Test
    public void returnErrorIfFirstNameIsEmpty() {
        when(request.getPersonFirstName()).thenReturn("");
        Optional<ValidationError> expected = Optional.of(
                new ValidationError("personFirstName", "Must not be empty!")
        );

        Optional<ValidationError> error = validation.check(request);
        assertEquals(expected, error);
    }

    @Test
    public void returnErrorIfFirstNameConsistsOfSpaces() {
        when(request.getPersonFirstName()).thenReturn("     ");
        Optional<ValidationError> expected = Optional.of(
                new ValidationError("personFirstName", "Must not be empty!")
        );

        Optional<ValidationError> error = validation.check(request);
        assertEquals(expected, error);
    }

    @Test
    public void returnNothingIfFirstNameContainsSpacesAndSmthElse() {
        when(request.getPersonFirstName()).thenReturn("    Name     ");
        Optional<ValidationError> expected = Optional.empty();

        Optional<ValidationError> error = validation.check(request);
        assertEquals(expected, error);
    }

    @Test
    public void returnNothingIfFirstNameIsOk() {
        when(request.getPersonFirstName()).thenReturn("Name");
        assertEquals(Optional.empty(), validation.check(request));
    }
}
