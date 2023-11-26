package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonFirstNameValidationTest {

    private final TravelRequestValidation validation = new PersonFirstNameValidation();

    @Test
    public void shouldReturnErrorIfPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(" ");

        Optional<ValidationError> optionalError = validation.execute(request);

        assertTrue(optionalError.isPresent());

        ValidationError validationError = optionalError.get();
        assertEquals("personFirstName", validationError.getField());
        assertEquals("Must not be empty!", validationError.getMessage());
    }

    @Test
    public void shouldReturnErrorIfPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);

        Optional<ValidationError> optionalError = validation.execute(request);

        assertTrue(optionalError.isPresent());

        ValidationError validationError = optionalError.get();
        assertEquals("personFirstName", validationError.getField());
        assertEquals("Must not be empty!", validationError.getMessage());
    }

    @Test
    public void shouldReturnNoError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("personFirstName");

        Optional<ValidationError> optionalError = validation.execute(request);

        assertTrue(optionalError.isEmpty());
    }
}
