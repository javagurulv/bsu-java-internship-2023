package lv.javaguru.travel.insurance.core.validationTests;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import lv.javaguru.travel.insurance.rest.validation.TravelRequestFirstNameValidation;
import lv.javaguru.travel.insurance.rest.validation.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

import lv.javaguru.travel.insurance.rest.TravelRequestValidation;
import lv.javaguru.travel.insurance.core.ValidationError;

import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TravelCalculatePremiumRequestFirstNameValidatorTest {
    private TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
    @InjectMocks
    private TravelRequestValidation validator = new TravelRequestFirstNameValidation();
    @Mock
    private ValidationErrorFactory errorFactory;
    private boolean isEqual(ValidationError e1, ValidationError e2) {
        return e1.getErrorCode().equals(e2.getErrorCode()) && e1.getDescription().equals(e2.getDescription());
    }
    /*
    @Test
    public void TravelCalculatePremiumRequestValidatorLastNameTest() {
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("Last Name");


        String errorCode = "ERROR_CODE_2";
        String description = "Field personLastName is empty!";


        //    when(env.getProperty(errorCode)).thenReturn(description);
        Optional<ValidationError> error = validator.validate(request);
        //this.beforeForNotNullTests();
        //ValidationError error = errors.get(0);
            assertTrue("",isEqual(error.get(), new ValidationError(errorCode, description)));
    }

     */
}
