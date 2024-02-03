package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorImplTest {
    private TravelCalculatePremiumRequestValidator validator;
    @Test
    public void noErrors(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation = mock(TravelRequestValidation.class);
        when(validation.execute(request)).thenReturn(Optional.empty());
        List<TravelRequestValidation> validations = List.of(validation);
        validator = new TravelCalculatePremiumRequestValidatorImpl(validations);
        validator.validate(request);
        assertEquals(validator.validate(request).size(), 0);
    }

    @Test
    public void errors(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation = mock(TravelRequestValidation.class);
        when(validation.execute(request)).thenReturn(Optional.of(new ValidationError()));
        List<TravelRequestValidation> validations = List.of(validation);
        validator = new TravelCalculatePremiumRequestValidatorImpl(validations);
        validator.validate(request);
        assertEquals(validator.validate(request).size(), 1);
    }

}
