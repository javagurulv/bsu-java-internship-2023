package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestCountryNotNullValidationTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private TravelRequestCountryNotNullValidation validation;

    @Test
    public void returnErrorIfCountryIsNullAndTravelMedicalRiskIsPresent() {
        when(request.getCountry()).thenReturn(null);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(errorFactory.buildError(any())).thenReturn(new ValidationError());

        Optional<ValidationError> expected = Optional.of(new ValidationError());
        Optional<ValidationError> error = validation.check(request);

        assertEquals(expected, error);
    }

    @Test
    public void DoNotCheckCountryIfTravelMedicalRiskIsNotPresent() {
        when(request.getSelectedRisks()).thenReturn(List.of("LOSS_BAGGAGE"));
        Optional<ValidationError> error = validation.check(request);
        assertEquals(Optional.empty(), error);
    }

    @Test
    public void returnNothingIfCountryIsNotNull() {
        when(request.getCountry()).thenReturn("SMTH");
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));

        Optional<ValidationError> error = validation.check(request);
        assertEquals(Optional.empty(), error);
    }

    @Test
    public void returnNothingIfSelectedRisksNull() {
        Optional<ValidationError> error = validation.check(request);
        assertEquals(Optional.empty(), error);
    }
}
