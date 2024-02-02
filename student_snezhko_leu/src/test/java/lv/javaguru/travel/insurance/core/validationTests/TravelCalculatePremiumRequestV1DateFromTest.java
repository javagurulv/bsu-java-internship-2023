package lv.javaguru.travel.insurance.core.validationTests;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.rest.TravelRequestValidation;
import lv.javaguru.travel.insurance.rest.validation.TravelRequestDateFromValidation;
import lv.javaguru.travel.insurance.rest.validation.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class TravelCalculatePremiumRequestV1DateFromTest {
    private TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
    @InjectMocks
    private TravelRequestValidation validator = new TravelRequestDateFromValidation();
    @Mock
    private ValidationErrorFactory errorFactory = mock(ValidationErrorFactory.class);
    public static boolean isEqual(ValidationError e1, ValidationError e2) {
        return e1.getErrorCode().equals(e2.getErrorCode()) && e1.getDescription().equals(e2.getDescription());
    }

    @Test
    public void TravelCalculatePremiumRequestValidatorDateFromNotEmptyTest() {
        when(request.getPersonFirstName()).thenReturn("First Name");
        when(request.getPersonLastName()).thenReturn("Last Name");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(Date.valueOf("2026-10-03"));
        when(request.getSelectedRisks()).thenReturn(List.of("MEDICAL RISK"));

        String errorCode = "ERROR_CODE_3";
        String description = "Field agreementDateFrom is empty!";

        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationError(errorCode, description));
        ReflectionTestUtils.setField(validator, "errorFactory", errorFactory);

        Optional<ValidationError> error = validator.validate(request);
        assertTrue("",isEqual(error.get(), new ValidationError(errorCode, description)));
    }

    @Test
    public void TravelCalculatePremiumRequestValidatorDateFromNotInPastTest() {
        when(request.getPersonFirstName()).thenReturn("First Name");
        when(request.getPersonLastName()).thenReturn("Last Name");
        when(request.getAgreementDateFrom()).thenReturn(Date.valueOf("2000-01-01"));
        when(request.getAgreementDateTo()).thenReturn(Date.valueOf("2026-10-03"));
        when(request.getSelectedRisks()).thenReturn(List.of("MEDICAL RISK"));

        String errorCode = "ERROR_CODE_4";
        String description = "Field agreementDateFrom is in the past!";

        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationError(errorCode, description));
        ReflectionTestUtils.setField(validator, "errorFactory", errorFactory);

        Optional<ValidationError> error = validator.validate(request);
        assertTrue("",isEqual(error.get(), new ValidationError(errorCode, description)));
    }

}
