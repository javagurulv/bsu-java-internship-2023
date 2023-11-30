package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRequestBirthdayValidationTest {
    @InjectMocks
    TravelRequestBirthdayValidation birthdayValidation;
    @Mock
    TravelCalculatePremiumRequestV1 request;
    @Mock
    ValidationErrorFactory validationErrorFactory;
    @Test
    public void responseShouldContainsErrorBirthdayInFutureTest(){
        ValidationError validationError = mock(ValidationError.class);
        when(request.getBirthday()).thenReturn(null);
        when(validationErrorFactory.buildError("ERROR_CODE_12")).thenReturn(validationError);
        Optional<ValidationError> error= birthdayValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
}
