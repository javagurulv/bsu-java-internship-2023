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

public class TravelRequestPersonLastNameValidationTest {
    @InjectMocks
    private TravelRequestPersonLastNameValidation personLastNameValidation;
    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @Test
    public void responseShouldContainErrorEmptyLastNameTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonLastName()).thenReturn("");
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_2")).thenReturn(validationError);
        Optional<ValidationError> error = personLastNameValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }

    @Test
    public void responseShouldContainErrorNullLastNameTest() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonLastName()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError("ERROR_CODE_2")).thenReturn(validationError);
        Optional<ValidationError> error = personLastNameValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get(), validationError);
    }
}
