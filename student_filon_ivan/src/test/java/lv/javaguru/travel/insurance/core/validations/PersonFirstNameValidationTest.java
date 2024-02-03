package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonFirstNameValidationTest {
    private TravelCalculatePremiumRequest request;
    private PersonFirstNameValidation validation = new PersonFirstNameValidation();

    @Test
    public void nullPointer(){
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get().getField(), "personFirstName");
        assertEquals(errors.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void emptyFirstName(){
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get().getField(), "personFirstName");
        assertEquals(errors.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void normalTest(){
        request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Ivan");
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isEmpty());
    }
}
