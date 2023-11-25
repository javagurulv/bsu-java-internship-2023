package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidatorImpl;
import lv.javaguru.travel.insurance.core.validations.TravelRequestValidation;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {

    @InjectMocks private TravelCalculatePremiumRequestValidatorImpl validator;

    @Test
    public void returnNothingIfEverythingIsOk() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.check(request)).thenReturn(Optional.empty());

        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.check(request)).thenReturn(Optional.empty());

        List<TravelRequestValidation> validations = List.of(
                validation1, validation2
        );

        ReflectionTestUtils.setField(validator, "validations", validations);
        List<ValidationError> errors = validator.validate(request);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void returnErrorsIfEverythingNotOk() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.check(request)).thenReturn(Optional.of(
                new ValidationError("field", "message")
        ));

        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.check(request)).thenReturn(Optional.of(
                new ValidationError("field", "message")
        ));

        List<TravelRequestValidation> validations = List.of(
                validation1, validation2
        );

        ReflectionTestUtils.setField(validator, "validations", validations);
        List<ValidationError> errors = validator.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 2);
    }
}
