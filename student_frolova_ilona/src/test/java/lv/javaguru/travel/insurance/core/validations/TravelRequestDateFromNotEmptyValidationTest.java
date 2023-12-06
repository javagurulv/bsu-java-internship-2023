package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorManager;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestDateFromNotEmptyValidationTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private ErrorManager errorManager;

    @InjectMocks
    private TravelRequestDateFromNotEmptyValidation validation;

    @Test
    public void returnErrorIfDateFromNotOk() {
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(errorManager.getErrorDescription(any())).thenReturn("description");

        Optional<ValidationError> expected = Optional.of(
                new ValidationError(
                        "ERROR_CODE_2",
                        "description"
                )
        );

        Optional<ValidationError> error = validation.check(request);
        assertEquals(expected, error);
    }

    @Test
    public void returnNothingIfDateFromIsOk() {
        when(request.getAgreementDateFrom()).thenReturn(new Date());

        Optional<ValidationError> error = validation.check(request);
        assertEquals(Optional.empty(), error);
    }
}
