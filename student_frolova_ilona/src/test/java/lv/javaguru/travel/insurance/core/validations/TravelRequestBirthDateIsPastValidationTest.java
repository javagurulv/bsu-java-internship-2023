package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
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
public class TravelRequestBirthDateIsPastValidationTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private ValidationErrorFactory errorFactory;

    @Mock
    private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private TravelRequestBirthDateIsPastValidation validation;

    @Test
    public void returnErrorIfBirthDateIsNotFromPast() {
        when(request.getPersonBirthDate()).thenReturn(new Date(4546941147000L));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(new Date(1000L));
        when(errorFactory.buildError(any())).thenReturn(new ValidationError());

        Optional<ValidationError> expected = Optional.of(new ValidationError());
        Optional<ValidationError> error = validation.check(request);

        assertEquals(expected, error);
    }

    @Test
    public void returnNothingIfBirthDateIsFromThePast() {
        when(request.getPersonBirthDate()).thenReturn(new Date(500L));
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(new Date(1000L));

        Optional<ValidationError> error = validation.check(request);
        assertEquals(Optional.empty(), error);
    }
}
