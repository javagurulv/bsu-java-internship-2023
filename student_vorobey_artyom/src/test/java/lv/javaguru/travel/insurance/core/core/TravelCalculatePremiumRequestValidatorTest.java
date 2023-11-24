package lv.javaguru.travel.insurance.core.core;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.core.validation.TravelRequestValidation;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static lv.javaguru.travel.insurance.core.DateFunctions.createDateFromString;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {

    @InjectMocks
    private TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void shouldNotReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validator1 = mock(TravelRequestValidation.class);
        TravelRequestValidation validator2 = mock(TravelRequestValidation.class);
        when(validator1.validateArg(request)).thenReturn(Optional.empty());
        when(validator2.validateArg(request)).thenReturn(Optional.empty());
        List<TravelRequestValidation> listOfValidators = List.of(validator1, validator2);
        ReflectionTestUtils.setField(requestValidator, "travelRequestValidations", listOfValidators);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validator1 = mock(TravelRequestValidation.class);
        TravelRequestValidation validator2 = mock(TravelRequestValidation.class);
        when(validator1.validateArg(request)).thenReturn(Optional.of(new ValidationError()));
        when(validator2.validateArg(request)).thenReturn(Optional.of(new ValidationError()));
        List<TravelRequestValidation> listOfValidators = List.of(validator1, validator2);
        ReflectionTestUtils.setField(requestValidator, "travelRequestValidations", listOfValidators);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 2);
    }
}

