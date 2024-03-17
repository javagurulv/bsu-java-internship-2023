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
public class ValidateIfSelectedRisksEmptyTest {
    @InjectMocks
    private ValidateIfSelectedRisksEmpty validator = new ValidateIfSelectedRisksEmpty();
    @Mock
    private ValidationErrorFactory factoryMock;
    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private ValidationError validationErrorMock;
    @Test
    public void selectedRisksShouldNotBeNull() {
        when(requestMock.getSelected_risks()).thenReturn(null);
        when(factoryMock.createError("ERROR_CODE_8")).thenReturn(validationErrorMock);
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get(), validationErrorMock);
    }

    @Test
    public void selectedRisksShouldNotBeEmpty() {
        when(requestMock.getSelected_risks()).thenReturn(Collections.emptyList());
        when(factoryMock.createError("ERROR_CODE_8")).thenReturn(validationErrorMock);
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isPresent());
        assertEquals(errorOptional.get(), validationErrorMock);
    }

    @Test
    public void shouldNotReturnErrorWhenselectedRisksIsPresent() {
        when(requestMock.getSelected_risks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_SPORT_ACTIVITIES"));
        Optional<ValidationError> errorOptional = validator.validate(requestMock);
        assertTrue(errorOptional.isEmpty());
        verifyNoInteractions(factoryMock, validationErrorMock);
    }
}
