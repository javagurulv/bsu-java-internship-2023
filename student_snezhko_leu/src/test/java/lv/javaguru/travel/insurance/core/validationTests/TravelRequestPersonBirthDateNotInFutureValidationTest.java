package lv.javaguru.travel.insurance.core.validationTests;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.rest.validation.TravelRequestPersonBirthDateNotInFutureValidation;
import lv.javaguru.travel.insurance.rest.validation.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelRequestPersonBirthDateNotInFutureValidationTest {
    @InjectMocks
    private TravelRequestPersonBirthDateNotInFutureValidation validator = new TravelRequestPersonBirthDateNotInFutureValidation();

    @Mock
    private ValidationErrorFactory errorFactory = mock(ValidationErrorFactory.class);

    private TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

    @Test
    public void personBirthDateNotInFutureValidationTest() {
        String risk = "TRAVEL_CANCELLATION";
        List<String> selectedRisk = new ArrayList<>();
        selectedRisk.add(risk);

        when(request.getPersonFirstName()).thenReturn("FirstName");
        when(request.getPersonLastName()).thenReturn("LastName");
        when(request.getAgreementDateFrom()).thenReturn(Date.valueOf("2030-05-03"));
        when(request.getAgreementDateTo()).thenReturn(Date.valueOf("2030-05-05"));
        when(request.getCountry()).thenReturn("LATVIA");
        when(request.getSelectedRisks()).thenReturn(selectedRisk);
        when(request.getPersonBirthDate()).thenReturn(Date.valueOf("2026-04-05"));

        String errorCode = "ERROR_CODE_13";
        String errorDescription = "Value of field birthDate is in the future!";
        ValidationError expectedError = new ValidationError(errorCode, errorDescription);

        when(errorFactory.buildError(errorCode)).thenReturn(expectedError);
        ReflectionTestUtils.setField(validator, "errorFactory", errorFactory);

        assertTrue(compareErrors(expectedError, validator.validate(request).get()));
    }

    private boolean compareErrors(ValidationError error1, ValidationError error2) {
        return error1.getErrorCode().equals(error2.getErrorCode())
                && error1.getDescription().equals(error2.getDescription());
    }
}
