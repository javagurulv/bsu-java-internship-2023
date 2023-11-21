package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorCodesPropertiesReader;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class TravelRequestSelectedRisksValidationTest {
    @InjectMocks
    TravelRequestSelectedRisksValidation selectedRisksValidation;
    @Mock
    private ErrorCodesPropertiesReader reader;
    @Test
    public void responseShouldContainErrorEmptySelected_risksTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelected_risks()).thenReturn(List.of());
        when(reader.getDescription("ERROR_CODE_8")).thenReturn("error description");
        Optional<ValidationError> error= selectedRisksValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "ERROR_CODE_8");
        assertEquals(error.get().getDescription(), "error description");
    }
    @Test
    public void responseShouldContainErrorNullSelected_risksTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelected_risks()).thenReturn(null);
        when(reader.getDescription("ERROR_CODE_8")).thenReturn("error description");
        Optional<ValidationError> error= selectedRisksValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "ERROR_CODE_8");
        assertEquals(error.get().getDescription(), "error description");
    }
}
