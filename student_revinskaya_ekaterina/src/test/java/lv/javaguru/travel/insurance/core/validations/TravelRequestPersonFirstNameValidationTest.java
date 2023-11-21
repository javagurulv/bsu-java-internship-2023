package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorCodesPropertiesReader;
import org.junit.jupiter.api.Test;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)

public class TravelRequestPersonFirstNameValidationTest {
    @InjectMocks
    TravelRequestPersonFirstNameValidation personFirstNameValidation;

    @Mock private ErrorCodesPropertiesReader reader;
    @Test
    public void responseShouldContainErrorEmptyFirstNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(reader.getDescription("ERROR_CODE_1")).thenReturn("error description");
        Optional<ValidationError> error= personFirstNameValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "ERROR_CODE_1");
        assertEquals(error.get().getDescription(), "error description");
    }
    @Test
    public void responseShouldContainErrorNullFirstNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(reader.getDescription("ERROR_CODE_1")).thenReturn("error description");
        Optional<ValidationError> error= personFirstNameValidation.validate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "ERROR_CODE_1");
        assertEquals(error.get().getDescription(), "error description");
    }
}