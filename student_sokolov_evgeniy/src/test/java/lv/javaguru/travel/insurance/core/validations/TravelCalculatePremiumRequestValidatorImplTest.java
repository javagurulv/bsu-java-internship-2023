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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorImplTest {

    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl validator;

    @Test
    public void shouldNotReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.empty());
        when(validation1.validateList(request)).thenReturn(List.of());
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.empty());
        when(validation2.validateList(request)).thenReturn(List.of());
        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(validator, "travelValidations", travelValidations);
        List<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnSingleErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.of(new ValidationError()));
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.of(new ValidationError()));
        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(validator, "travelValidations", travelValidations);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
    }

    @Test
    public void shouldReturnListErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.empty());
        when(validation1.validateList(request)).thenReturn(List.of(new ValidationError()));
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.empty());
        when(validation2.validateList(request)).thenReturn(List.of(new ValidationError()));
        List<TravelRequestValidation> travelValidations = List.of(
                validation1, validation2
        );
        ReflectionTestUtils.setField(validator, "travelValidations", travelValidations);
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
    }

}