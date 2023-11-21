package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorCodesPropertiesReader;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

public class TravelRequestPersonLastNameValidationTest {
    @InjectMocks
    TravelRequestPersonLastNameValidation personLastNameValidation;
    @Mock
    private ErrorCodesPropertiesReader reader;
    @Test
    public void responseShouldContainErrorEmptyLastNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("");
        when(reader.getDescription("ERROR_CODE_2")).thenReturn("error description");
        Optional<ValidationError> error= personLastNameValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "ERROR_CODE_2");
        assertEquals(error.get().getDescription(), "error description");
    }
    @Test
    public void responseShouldContainErrorNullLastNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(null);
        when(reader.getDescription("ERROR_CODE_2")).thenReturn("error description");
        Optional<ValidationError> error= personLastNameValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "ERROR_CODE_2");
        assertEquals(error.get().getDescription(), "error description");
    }
}
