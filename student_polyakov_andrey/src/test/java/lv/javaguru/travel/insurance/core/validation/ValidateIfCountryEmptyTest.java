package lv.javaguru.travel.insurance.core.validation;

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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidateIfCountryEmptyTest {
    @Mock
    private ValidationErrorFactory errorFactory;
    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @InjectMocks
    private ValidateIfCountryEmpty validation;

    @Test
    public void shouldReturnNoErrorWhenSelectedRisksIsNull() {
        when(requestMock.getSelected_risks()).thenReturn(null);
        Optional<ValidationError> errorOptional = validation.validate(requestMock);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    public void shouldReturnNoErrorWhenSelectedRisksNotContainsTravelMedical() {
        when(requestMock.getSelected_risks()).thenReturn(List.of("TRAVEL_EVACUATION"));
        Optional<ValidationError> errorOptional = validation.validate(requestMock);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    public void shouldReturnNoErrorWhenSelectedRisksContainsTravelMedicalAndCountryIsPresent() {
        when(requestMock.getSelected_risks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(requestMock.getCountry()).thenReturn("SPAIN");
        Optional<ValidationError> errorOptional = validation.validate(requestMock);
        assertTrue(errorOptional.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenSelectedRisksContainsTravelMedicalAndCountryIsNull() {
        when(requestMock.getSelected_risks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(requestMock.getCountry()).thenReturn(null);
        when(errorFactory.createError("ERROR_CODE_10"))
                .thenReturn(new ValidationError("ERROR_CODE_10", "Country must be provided when TRAVEL_MEDICAL is selected"));
        Optional<ValidationError> errorOptional = validation.validate(requestMock);
        assertTrue(errorOptional.isPresent());
        assertEquals("ERROR_CODE_10", errorOptional.get().getErrorCode());
        assertEquals("Country must be provided when TRAVEL_MEDICAL is selected", errorOptional.get().getDescription());
    }

    @Test
    public void shouldReturnErrorWhenSelectedRisksContainsTravelMedicalAndCountryIsEmpty() {
        when(requestMock.getSelected_risks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(requestMock.getCountry()).thenReturn("");
        when(errorFactory.createError("ERROR_CODE_10"))
                .thenReturn(new ValidationError("ERROR_CODE_10", "Country must be provided when TRAVEL_MEDICAL is selected"));
        Optional<ValidationError> errorOptional = validation.validate(requestMock);
        assertTrue(errorOptional.isPresent());
        assertEquals("ERROR_CODE_10", errorOptional.get().getErrorCode());
        assertEquals("Country must be provided when TRAVEL_MEDICAL is selected", errorOptional.get().getDescription());
    }
}
