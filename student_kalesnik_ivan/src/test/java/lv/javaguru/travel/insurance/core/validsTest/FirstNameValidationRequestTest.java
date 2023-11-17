package lv.javaguru.travel.insurance.core.validsTest;

import lv.javaguru.travel.insurance.core.valids.FirstNameValidationRequest;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstNameValidationRequestTest {

    private FirstNameValidationRequest validation;

    @BeforeEach
    public void setUp() {
        validation = new FirstNameValidationRequest();
    }

    @Test
    public void shouldReturnErrorWhenFirstNameIsNull() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName(null);

        Optional<ValidationError> result = validation.execute(request);

        assertEquals("Must not be empty!", result.get().getMessage());
        assertEquals("personFirstName", result.get().getField());
    }

    @Test
    public void shouldReturnErrorWhenFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("");

        Optional<ValidationError> result = validation.execute(request);

        assertEquals("Must not be empty!", result.get().getMessage());
        assertEquals("personFirstName", result.get().getField());
    }

    @Test
    public void shouldReturnEmptyWhenFirstNameIsNotEmpty() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Java");

        Optional<ValidationError> result = validation.execute(request);

        assertEquals(Optional.empty(), result);
    }
}

