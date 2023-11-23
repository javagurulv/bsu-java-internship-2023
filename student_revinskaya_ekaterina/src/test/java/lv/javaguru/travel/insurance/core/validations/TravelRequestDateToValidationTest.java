package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

public class TravelRequestDateToValidationTest {
    @Mock private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    TravelRequestAgreementDateToValidation dateToValidation;
    @Test
    public void responseShouldContainErrorNullDateToTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_4")).thenReturn(validationError);
        Optional<ValidationError> error= dateToValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
}
