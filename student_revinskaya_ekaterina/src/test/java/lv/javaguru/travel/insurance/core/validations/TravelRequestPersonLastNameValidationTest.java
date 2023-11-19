package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelRequestPersonLastNameValidationTest {
    @Test
    public void responseShouldContainErrorEmptyLastNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestPersonLastNameValidation personLastNameValidation = new TravelRequestPersonLastNameValidation();
        when(request.getPersonLastName()).thenReturn("");
        Optional<ValidationError> error= personLastNameValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personLastName");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }
    @Test
    public void responseShouldContainErrorNullLastNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestPersonLastNameValidation personLastNameValidation = new TravelRequestPersonLastNameValidation();
        when(request.getPersonLastName()).thenReturn(null);
        Optional<ValidationError> error= personLastNameValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personLastName");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }
}
