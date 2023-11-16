package lv.javaguru.travel.insurance.core.validsTest;

import lv.javaguru.travel.insurance.core.valids.LastNameValidationRequest;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LastNameValidationRequestTest {

    private LastNameValidationRequest validation;

    @BeforeEach
    public void setUp() {
        validation = new LastNameValidationRequest();
    }

    @Test
    public void shouldReturnErrorWhenLastNameIsNull() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName(null);

        Optional<ValidationError> result = validation.execute(request);

        assertEquals("Must not be empty!", result.get().getMessage());
        assertEquals("personLastName", result.get().getField());
    }

    @Test
    public void shouldReturnErrorWhenLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("");

        Optional<ValidationError> result = validation.execute(request);

        assertEquals("Must not be empty!", result.get().getMessage());
        assertEquals("personLastName", result.get().getField());
    }

    @Test
    public void shouldReturnEmptyWhenLastNameIsNotEmpty() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("ILoveSpring");

        Optional<ValidationError> result = validation.execute(request);

        assertEquals(Optional.empty(), result);
    }
}

