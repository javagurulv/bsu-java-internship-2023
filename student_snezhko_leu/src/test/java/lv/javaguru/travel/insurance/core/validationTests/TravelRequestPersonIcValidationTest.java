package lv.javaguru.travel.insurance.core.validationTests;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.rest.validation.TravelRequestPersonIcValidation;
import lv.javaguru.travel.insurance.rest.validation.TravelRequestValidationImpl;
import lv.javaguru.travel.insurance.rest.validation.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.*;

@ExtendWith(SpringExtension.class)
public class TravelRequestPersonIcValidationTest {
    @Mock
    private ValidationErrorFactory errorFactory;

    @Mock
    private TravelCalculatePremiumRequestV1 request;

    @InjectMocks
    private TravelRequestPersonIcValidation validation;

    @Test
    public void personIcIsNull() {
        String errorCode = "ERROR_CODE_16";
        String description = "Field personIc is empty!";
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationError(errorCode, description));
        when(request.getPersonIc()).thenReturn(null);
        Optional<ValidationError> expectedError = validation.validate(request);
        assertNotEquals("", Optional.empty(), expectedError);
        assertEquals("", expectedError.get().getErrorCode(), errorCode);
        assertEquals("", expectedError.get().getDescription(), description);
    }

    @Test
    public void personIcIsEmpty() {
        String errorCode = "ERROR_CODE_16";
        String description = "Field personIc is empty!";
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationError(errorCode, description));
        when(request.getPersonIc()).thenReturn("");
        Optional<ValidationError> expectedError = validation.validate(request);
        assertNotEquals("", Optional.empty(), expectedError);
        assertEquals("", expectedError.get().getErrorCode(), errorCode);
        assertEquals("", expectedError.get().getDescription(), description);
    }

    @Test
    public void personIcIsCorrect() {
        String errorCode = "ERROR_CODE_16";
        String description = "Field personIc is empty!";
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationError(errorCode, description));
        when(request.getPersonIc()).thenReturn("PERSON_VALID");
        Optional<ValidationError> expectedError = validation.validate(request);
        assertEquals("", Optional.empty(), expectedError);
    }
}
