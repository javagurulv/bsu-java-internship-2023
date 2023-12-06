package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorManager;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
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
    ErrorManager errorManager;

    @InjectMocks
    private TravelRequestDateFromNotEmptyValidation validation;

    @BeforeEach
    public void initMocks() {
        when(errorManager.getErrorDescription(any())).thenReturn("description");
    }

    @Test
    public void returnErrorIfDateFromNotOk() {
        when(request.getAgreementDateFrom()).thenReturn(null);
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
