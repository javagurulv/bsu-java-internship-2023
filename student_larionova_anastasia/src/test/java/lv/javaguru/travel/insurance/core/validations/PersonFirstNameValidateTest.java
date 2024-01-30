package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonFirstNameValidateTest {

    private PersonFirstNameValidate validate = new PersonFirstNameValidate();

    @Test
    void validatorWhenPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> errors = validate.validator(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_6");
        assertEquals(errors.get().getDescription(), validate.errorCode6Message);
    }

    @Test
    void validatorWhenPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        Optional<ValidationError> errors = validate.validator(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), "ERROR_CODE_6");
        assertEquals(errors.get().getDescription(), validate.errorCode6Message);
    }

    @Test
    void validatorWhenPersonFirstNameIsExist() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Vasya");
        Optional<ValidationError> errors = validate.validator(request);
        assertFalse(errors.isPresent());
        assertEquals(request.getPersonFirstName(), "Vasya");
    }
}