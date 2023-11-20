package lv.javaguru.travel.insurance.core.validations;

import org.junit.jupiter.api.Test;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



public class TravelRequestPersonFirstNameValidationTest {
    @Test
    public void responseShouldContainErrorEmptyFirstNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestPersonFirstNameValidation personFirstNameValidation = new TravelRequestPersonFirstNameValidation();
        when(request.getPersonFirstName()).thenReturn("");
        Optional<ValidationError> error= personFirstNameValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personFirstName");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }
    @Test
    public void responseShouldContainErrorNullFirstNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestPersonFirstNameValidation personFirstNameValidation = new TravelRequestPersonFirstNameValidation();
        when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> error= personFirstNameValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personFirstName");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }
}