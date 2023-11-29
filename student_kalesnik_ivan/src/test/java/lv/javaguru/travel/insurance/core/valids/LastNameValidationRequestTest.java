package lv.javaguru.travel.insurance.core.valids;

import lv.javaguru.travel.insurance.validation.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LastNameValidationRequestTest {
    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks
    private LastNameValidationRequest validation;

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonLastName()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_8")).thenReturn(validationError);
        Optional<ValidationError> errorOpt = validation.validate(request);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonLastName()).thenReturn("");
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_8")).thenReturn(validationError);
        Optional<ValidationError> errorOpt = validation.validate(request);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsPresent() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getPersonLastName()).thenReturn("Petrov");
        Optional<ValidationError> errorOpt = validation.validate(request);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }

}

