package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static lv.javaguru.travel.insurance.core.util.DateFunctions.createDateFromString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgreementDateFromLessThanAgreementDateToValidatorTest {

    @Mock private ValidationErrorFactory factory;

    @InjectMocks
    private AgreementDateFromLessThanAgreementDateToValidator validator;

    @Test
    public void shouldReturnErrorIfAgreementDateFromAfterAgreementDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDateFromString("12.12.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDateFromString("12.12.2024"));
        ValidationError validationError = mock(ValidationError.class);
        when(factory.buildError("ERROR_CODE_5")).thenReturn(validationError);
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertSame(errorOptional.get(), validationError);
    }

    @Test
    public void shouldReturnErrorIfAgreementDateFromIsEqualToAgreementDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDateFromString("12.12.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDateFromString("12.12.2024"));
        ValidationError validationError = mock(ValidationError.class);
        when(factory.buildError("ERROR_CODE_5")).thenReturn(validationError);
        Optional<ValidationError> errorOptional = validator.validateArgs(request);
        assertTrue(errorOptional.isPresent());
        assertSame(errorOptional.get(), validationError);
    }
}
