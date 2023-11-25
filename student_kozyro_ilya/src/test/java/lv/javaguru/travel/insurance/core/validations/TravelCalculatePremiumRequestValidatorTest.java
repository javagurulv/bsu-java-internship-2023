package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {

    @InjectMocks
    TravelCalculatePremiumRequestValidatorImpl requestValidator;

    @Test
    void testAllOk() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        TravelRequestValidation v1 = mock(TravelRequestValidation.class);
        TravelRequestValidation v2 = mock(TravelRequestValidation.class);

        when(v1.execute(request)).thenReturn(Optional.empty());
        when(v2.execute(request)).thenReturn(Optional.empty());

        List<TravelRequestValidation> validations = List.of(v1, v2);

        ReflectionTestUtils.setField(requestValidator, "travelValidations", validations);

        var errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testOneError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        TravelRequestValidation v1 = mock(TravelRequestValidation.class);
        TravelRequestValidation v2 = mock(TravelRequestValidation.class);

        when(v1.execute(request)).thenReturn(Optional.empty());
        when(v2.execute(request)).thenReturn(Optional.of(new ValidationError()));

        List<TravelRequestValidation> validations = List.of(v1, v2);

        ReflectionTestUtils.setField(requestValidator, "travelValidations", validations);

        var errors = requestValidator.validate(request);
        assertEquals(1, errors.size());
    }

    @Test
    void testTwoErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        TravelRequestValidation v1 = mock(TravelRequestValidation.class);
        TravelRequestValidation v2 = mock(TravelRequestValidation.class);

        when(v1.execute(request)).thenReturn(Optional.of(new ValidationError()));
        when(v2.execute(request)).thenReturn(Optional.of(new ValidationError()));

        List<TravelRequestValidation> validations = List.of(v1, v2);

        ReflectionTestUtils.setField(requestValidator, "travelValidations", validations);

        var errors = requestValidator.validate(request);
        assertEquals(2, errors.size());
    }
}
