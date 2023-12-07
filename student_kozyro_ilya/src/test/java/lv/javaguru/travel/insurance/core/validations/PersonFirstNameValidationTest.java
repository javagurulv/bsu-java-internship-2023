package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.services.DateService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonFirstNameValidationTest {

    @Mock
    DateService dateService;

    @InjectMocks
    PersonFirstNameValidation validation;


    @Test
    void dontHaveMandatoryFirstName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);

        Optional<ValidationError> error = validation.execute(request);

        assertFalse(error.isEmpty());
        assertEquals("personFirstName", error.get().getErrorCode());
        assertEquals("Shouldn't be empty!", error.get().getErrorDescription());
    }

    @Test
    void haveEmptyFirstName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");

        Optional<ValidationError> error = validation.execute(request);

        assertFalse(error.isEmpty());
        assertEquals("personFirstName", error.get().getErrorCode());
        assertEquals("Shouldn't be empty!", error.get().getErrorDescription());
    }

    @Test
    void allOkFirstName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("SomeName");

        Optional<ValidationError> error = validation.execute(request);

        assertTrue(error.isEmpty());
    }


}
