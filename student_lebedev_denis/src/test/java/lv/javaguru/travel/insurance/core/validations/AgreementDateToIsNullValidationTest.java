package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementDateToIsNullValidationTest {

    private final DateTimeService dateTimeService = new DateTimeService();

    private final TravelRequestValidation validation = new AgreementDateToIsNullValidation();

    @Test
    public void shouldReturnErrorIfAgreementDateToIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(null);

        Optional<ValidationError> optionalValidationError = validation.execute(request);

        assertTrue(optionalValidationError.isPresent());

        ValidationError validationError = optionalValidationError.get();
        assertEquals("agreementDateTo", validationError.getField());
        assertEquals("Must not be empty!", validationError.getMessage());
    }

    @Test
    public void shouldReturnNoError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(dateTimeService.createDate("20.01.2036"));

        Optional<ValidationError> optionalValidationError = validation.execute(request);

        assertTrue(optionalValidationError.isEmpty());
    }
}
