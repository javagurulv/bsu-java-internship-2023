package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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
    private TravelRequestSelectedRisksValidation selectedRisksValidation;
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Test
    public void responseShouldContainErrorEmptySelected_risksTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getSelectedRisks()).thenReturn(List.of());
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_8")).thenReturn(validationError);
        Optional<ValidationError> error= selectedRisksValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
    @Test
    public void responseShouldContainErrorNullSelected_risksTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getSelectedRisks()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_8")).thenReturn(validationError);
        Optional<ValidationError> error= selectedRisksValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
}
