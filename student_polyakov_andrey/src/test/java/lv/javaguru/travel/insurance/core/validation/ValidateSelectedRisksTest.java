package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidateSelectedRisksTest {
    @InjectMocks
    private ValidateSelectedRisks validator = new ValidateSelectedRisks();
    @Mock
    private ValidationErrorFactory factory;
    @Test
    public void selectedRisksShouldNotBeNull() {
        TravelCalculatePremiumRequest requestMock = mock(TravelCalculatePremiumRequest.class);
        ValidationError validationErrorMock = mock(ValidationError.class);
        when(requestMock.getSelected_risks()).thenReturn(null);
        when(factory.createError("ERROR_CODE_8")).thenReturn(validationErrorMock);
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get(), validationErrorMock);
    }

    @Test
    public void selectedRisksShouldNotBeEmpty() {
        TravelCalculatePremiumRequest requestMock = mock(TravelCalculatePremiumRequest.class);
        ValidationError validationErrorMock = mock(ValidationError.class);
        when(requestMock.getSelected_risks()).thenReturn(Collections.emptyList());
        when(factory.createError("ERROR_CODE_8")).thenReturn(validationErrorMock);
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get(), validationErrorMock);
    }

    @Test
    public void shouldNotReturnErrorWhenselectedRisksIsPresent() {
        TravelCalculatePremiumRequest requestMock = mock(TravelCalculatePremiumRequest.class);
        when(requestMock.getSelected_risks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_SPORT_ACTIVITIES"));
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isEmpty());
        verifyNoInteractions(factory);
    }
}
