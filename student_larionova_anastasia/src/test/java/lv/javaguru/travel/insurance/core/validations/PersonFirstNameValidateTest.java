package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonFirstNameValidateTest {

    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks  PersonFirstNameValidate validate;


    @Test
    void validatorWhenPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_6")).thenReturn(validationError);
        Optional<ValidationError> errors = validate.validator(request);
        assertTrue(errors.isPresent());
        assertSame(errors.get(), validationError);
    }

    @Test
    void validatorWhenPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_6")).thenReturn(validationError);
        Optional<ValidationError> errors = validate.validator(request);
        assertTrue(errors.isPresent());
        assertSame(errors.get(), validationError);
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