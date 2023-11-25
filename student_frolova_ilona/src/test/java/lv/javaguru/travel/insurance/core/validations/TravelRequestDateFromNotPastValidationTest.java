package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestDateFromNotPastValidationTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @InjectMocks
    private TravelRequestDateFromNotPastValidation validation;

    @Test
    public void returnErrorIfDateFromIsFromThePast() {
        when(request.getAgreementDateFrom()).thenReturn(
                new Date(validation.getMillisecondsNow() - 172800000L)
        );

        Optional<ValidationError> expected = Optional.of(
            new ValidationError(
                    "agreementDateFrom", "Must not be from the past!"
            )
        );

        Optional<ValidationError> error = validation.check(request);
        assertEquals(expected, error);
    }

    @Test
    public void returnNothingIfDateFromIsNotFromThePast() {
        when(request.getAgreementDateFrom()).thenReturn(
                new Date(validation.getMillisecondsNow() + 1500L)
        );

        Optional<ValidationError> error = validation.check(request);
        assertEquals(Optional.empty(), error);
    }
}
