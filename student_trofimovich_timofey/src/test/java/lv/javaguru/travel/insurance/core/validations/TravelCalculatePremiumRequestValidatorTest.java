package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidatorImplementation;
import lv.javaguru.travel.insurance.core.validations.TravelRequestValidation;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)


public class TravelCalculatePremiumRequestValidatorTest {

    private TravelCalculatePremiumRequestValidatorImplementation requestValidator;



    @Test
    void shouldNotReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.empty());
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.empty());
        List<TravelRequestValidation> validations = List.of(validation1, validation2);
        requestValidator = new TravelCalculatePremiumRequestValidatorImplementation(validations);
        List<ValidationError> errors = requestValidator.validate(request);
        assertThat(errors.size()).isEqualTo(0);
    }

    @Test
    void shouldReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.of(new ValidationError()));
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.of(new ValidationError()));
        List<TravelRequestValidation> validations = List.of(validation1, validation2);
        requestValidator = new TravelCalculatePremiumRequestValidatorImplementation(validations);
        List<ValidationError> errors = requestValidator.validate(request);
        assertThat(errors.size()).isGreaterThan(0);
    }

}
