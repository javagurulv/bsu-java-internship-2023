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
public class TravelRequestLastNameValidationTest {
    @Mock
    private TravelCalculatePremiumRequest request;

    @InjectMocks
    private TravelRequestLastNameValidation validation;

    @Test
    public void returnErrorIfLastNameIsEmpty() {
        when(request.getPersonLastName()).thenReturn("");
        Optional<ValidationError> expected = Optional.of(
                new ValidationError("personLastName", "Must not be empty!")
        );
        assertEquals(expected, validation.check(request));
    }

    @Test
    public void returnErrorIfLastNameConsistsOfSpaces() {
        when(request.getPersonLastName()).thenReturn("     ");
        Optional<ValidationError> expected = Optional.of(
                new ValidationError("personLastName", "Must not be empty!")
        );
        assertEquals(expected, validation.check(request));
    }

    @Test
    public void returnNothingIfLastNameContainsSpacesAndSmthElse() {
        when(request.getPersonLastName()).thenReturn("    Name     ");
        assertEquals(Optional.empty(), validation.check(request));
    }

    @Test
    public void returnNothingIfLastNameIsOk() {
        when(request.getPersonLastName()).thenReturn("Name");
        assertEquals(Optional.empty(), validation.check(request));
    }
}
