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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TravelRequestLastNameValidationTest {
    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private TravelRequestLastNameValidation validation;

    @Test
    public void returnErrorIfLastNameIsEmpty() {
        when(request.getPersonLastName()).thenReturn("");
        when(errorFactory.buildError(any())).thenReturn(new ValidationError());

        Optional<ValidationError> expected = Optional.of(new ValidationError());
        assertEquals(expected, validation.check(request));
    }

    @Test
    public void returnErrorIfLastNameConsistsOfSpaces() {
        when(request.getPersonLastName()).thenReturn("     ");
        when(errorFactory.buildError(any())).thenReturn(new ValidationError());

        Optional<ValidationError> expected = Optional.of(new ValidationError());
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
