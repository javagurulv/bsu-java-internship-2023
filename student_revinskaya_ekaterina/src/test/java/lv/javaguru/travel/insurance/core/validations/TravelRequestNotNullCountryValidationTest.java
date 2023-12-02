package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getCountry()).thenReturn("");
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_10"), anyList())).thenReturn(validationError);
        Optional<ValidationError> error = notNullCountryValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }

    @Test
    public void responseShouldContainErrorNullCountryTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getCountry()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_10"), anyList())).thenReturn(validationError);
        Optional<ValidationError> error = notNullCountryValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
}