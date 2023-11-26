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
public class AgreementDateFromIsNullValidationTest {

    private final DateTimeService dateTimeService = new DateTimeService();

    private final TravelRequestValidation validation = new AgreementDateFromIsNullValidation();

    @Test
    public void shouldReturnErrorIfAgreementDateFromIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(null);

        Optional<ValidationError> optionalValidationError = validation.execute(request);

        assertTrue(optionalValidationError.isPresent());

        ValidationError validationError = optionalValidationError.get();
        assertEquals("agreementDateFrom", validationError.getField());
        assertEquals("Must not be empty!", validationError.getMessage());
    }

    @Test
    public void shouldReturnNoError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(dateTimeService.createDate("22.12.2034"));

        Optional<ValidationError> optionalValidationError = validation.execute(request);

        assertTrue(optionalValidationError.isEmpty());
    }
}
