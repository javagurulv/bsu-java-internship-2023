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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class TravelRequestNotNullCountryValidationTest {
    @InjectMocks
    private TravelRequestNotNullCountryValidation notNullCountryValidation;

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Test
    public void responseShouldContainErrorEmptyCountryTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn("");
        when(request.getSelected_risks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_10"), anyList())).thenReturn(validationError);
        Optional<ValidationError> error= notNullCountryValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
    @Test
    public void responseShouldContainErrorNullCountryTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn(null);
        when(request.getSelected_risks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_10"), anyList())).thenReturn(validationError);
        Optional<ValidationError> error= notNullCountryValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
    @Test
    public void responseShouldNotContainErrorNullCountryTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn(null);
        when(request.getSelected_risks()).thenReturn(List.of("NOT_MEDICAL_RISK"));
        Optional<ValidationError> error= notNullCountryValidation.validate(request);
        assertTrue(error.isEmpty());
    }
}