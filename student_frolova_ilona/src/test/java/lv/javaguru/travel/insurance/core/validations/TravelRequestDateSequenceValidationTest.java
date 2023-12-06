package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorManager;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestDateSequenceValidationTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private ErrorManager errorManager;

    @InjectMocks
    private TravelRequestDateSequenceValidation validation;

    @Test
    public void returnErrorIfDateFromAfterDateTo() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(200L));
        when(request.getAgreementDateTo()).thenReturn(new Date(100L));
        when(errorManager.getErrorDescription(any())).thenReturn("description");

        Optional<ValidationError> expected = Optional.of(
                new ValidationError(
                        "ERROR_CODE_5", "description"
                )
        );

        Optional<ValidationError> error = validation.check(request);
        assertEquals(expected, error);
    }

    @Test
    public void returnNothingIfDateFromBeforeDateTo() {
        when(request.getAgreementDateFrom()).thenReturn(new Date(100L));
        when(request.getAgreementDateTo()).thenReturn(new Date(200L));

        Optional<ValidationError> error = validation.check(request);
        assertEquals(Optional.empty(), error);
        request.setAgreementDateTo(request.getAgreementDateFrom());
        assertEquals(Optional.empty(), error);
    }
}
