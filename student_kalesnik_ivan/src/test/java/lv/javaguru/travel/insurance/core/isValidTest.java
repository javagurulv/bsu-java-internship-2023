package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumRequestValidatorTest {

    @InjectMocks
    private TravelCalculatePremiumRequestValidator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnErrorWhenFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("");

        List<ValidationError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getErrorMessage());
    }

    @Test
    void shouldReturnNoErrorsWhenFirstNameIsNotEmpty() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Java");

        List<ValidationError> errors = validator.validate(request);

        assertEquals(0, errors.size());
    }
}
