package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementDateToInFutureValidationTest {

    private final DateTimeService dateTimeService = new DateTimeService();

    @Mock
    private DateTimeService dateTimeServiceMock;

    @InjectMocks
    private AgreementDateToInFutureValidation validation;

    @Test
    public void shouldReturnErrorIfAgreementDateToInPast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(dateTimeService.createDate("30.01.2015"));
        when(dateTimeServiceMock.getCurrentDate()).thenReturn(new Date());

        Optional<ValidationError> optionalValidationError = validation.execute(request);

        assertTrue(optionalValidationError.isPresent());

        ValidationError validationError = optionalValidationError.get();
        assertEquals("agreementDateTo", validationError.getField());
        assertEquals("Must be in future!", validationError.getMessage());
    }

    @Test
    public void shouldReturnNoError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(dateTimeService.createDate("30.01.2035"));
        when(dateTimeServiceMock.getCurrentDate()).thenReturn(new Date());

        Optional<ValidationError> optionalValidationError = validation.execute(request);

        assertTrue(optionalValidationError.isEmpty());
    }
}
