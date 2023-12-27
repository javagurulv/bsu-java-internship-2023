package lv.javaguru.travel.insurance.core.validations;

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
public class TravelRequestDateToNotPastValidationTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private TravelRequestDateToNotPastValidation validation;

    @Test
    public void returnErrorIfDateToIsFromThePast() {
        when(request.getAgreementDateTo()).thenReturn(
                new Date(validation.getMillisecondsNow() - 172800000L)
        );
        when(errorFactory.buildError(any())).thenReturn(new ValidationError());

        Optional<ValidationError> expected = Optional.of(new ValidationError());
        Optional<ValidationError> error = validation.check(request);

        assertEquals(expected, error);
    }

    @Test
    public void returnNothingIfDateToIsNotFromThePast() {
        when(request.getAgreementDateTo()).thenReturn(
                new Date(validation.getMillisecondsNow() + 172800000L)
        );
        assertEquals(Optional.empty(), validation.check(request));
    }
}
