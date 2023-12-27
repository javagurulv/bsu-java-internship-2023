package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.DateServiceUtil;
import lv.javaguru.travel.insurance.core.services.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static lv.javaguru.travel.insurance.core.validations.errors.ValidationErrorCodes.MANDATORY_FIELD_MISSING;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonLastNameValidationTest {

    @Mock
    ValidationErrorFactory validationErrorFactory;
    @Mock
    DateServiceUtil dateService;

    @InjectMocks
    PersonLastNameValidation validation;


    @Test
    void dontHaveMandatoryFirstName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(null);

        when(validationErrorFactory.buildError(eq(MANDATORY_FIELD_MISSING), argThat(isPlaceHolder())))
                .thenReturn(new ValidationError(MANDATORY_FIELD_MISSING, "description"));


        Optional<ValidationError> error = validation.execute(request);

        assertFalse(error.isEmpty());
        assertEquals(MANDATORY_FIELD_MISSING, error.get().getErrorCode());
        assertEquals("description", error.get().getErrorDescription());
    }

    @Test
    void haveEmptyLastName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("");

        when(validationErrorFactory.buildError(eq(MANDATORY_FIELD_MISSING), argThat(isPlaceHolder())))
                .thenReturn(new ValidationError(MANDATORY_FIELD_MISSING, "description"));


        Optional<ValidationError> error = validation.execute(request);

        assertFalse(error.isEmpty());
        assertEquals(MANDATORY_FIELD_MISSING, error.get().getErrorCode());
        assertEquals("description", error.get().getErrorDescription());
    }

    @Test
    void allOkLastName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("SomeName");

        Optional<ValidationError> error = validation.execute(request);

        assertTrue(error.isEmpty());
    }

    private ArgumentMatcher<Placeholder> isPlaceHolder() {
        return argument -> argument.getKey().equals("fieldName") && argument.getValue().equals("personLastName");
    }

}
