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
public class PersonLastNameValidationTest {

    private final TravelRequestValidation validation = new PersonLastNameValidation();

    @Test
    public void shouldReturnErrorIfPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(" ");

        Optional<ValidationError> optionalError = validation.execute(request);

        assertTrue(optionalError.isPresent());

        ValidationError validationError = optionalError.get();
        assertEquals("personLastName", validationError.getField());
        assertEquals("Must not be empty!", validationError.getMessage());
    }

    @Test
    public void shouldReturnErrorIfPersonLastNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(null);

        Optional<ValidationError> optionalError = validation.execute(request);

        assertTrue(optionalError.isPresent());

        ValidationError validationError = optionalError.get();
        assertEquals("personLastName", validationError.getField());
        assertEquals("Must not be empty!", validationError.getMessage());
    }

    @Test
    public void shouldReturnNoError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("personLastName");

        Optional<ValidationError> optionalError = validation.execute(request);

        assertTrue(optionalError.isEmpty());
    }
}
