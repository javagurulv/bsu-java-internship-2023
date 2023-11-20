package lv.javaguru.travel.insurance.core.validsTest;

import lv.javaguru.travel.insurance.core.ErrorCodeValueUtil;
import lv.javaguru.travel.insurance.core.valids.LastNameValidationRequest;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LastNameValidationRequestTest {

    @Mock
    private ErrorCodeValueUtil errorCodeUtil;

    @InjectMocks
    private LastNameValidationRequest validation;

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(null);
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_8")).thenReturn("error description");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getErrorCode(), "ERROR_CODE_8");
        assertEquals(errorOpt.get().getDescription(), "error description");
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("");
        when(errorCodeUtil.getErrorDescription("ERROR_CODE_8")).thenReturn("error description");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getErrorCode(), "ERROR_CODE_8");
        assertEquals(errorOpt.get().getDescription(), "error description");
    }

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("Petrov");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorCodeUtil);
    }

}

