package lv.javaguru.travel.insurance.core.validationTests;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import lv.javaguru.travel.insurance.rest.validation.TravelRequestFirstNameValidation;
import lv.javaguru.travel.insurance.rest.validation.ValidationErrorFactory;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import lv.javaguru.travel.insurance.rest.TravelRequestValidation;
import lv.javaguru.travel.insurance.core.ValidationError;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

public class TravelCalculatePremiumRequestV1FirstNameValidatorTest {
    private TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
    @InjectMocks
    private TravelRequestValidation validator = new TravelRequestFirstNameValidation();
    @Mock
    private ValidationErrorFactory errorFactory = mock(ValidationErrorFactory.class);
    public static boolean isEqual(ValidationError e1, ValidationError e2) {
        return e1.getErrorCode().equals(e2.getErrorCode()) && e1.getDescription().equals(e2.getDescription());
    }

    @Test
    public void TravelCalculatePremiumRequestValidatorFirstNameTest() {
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("Last Name");
        when(request.getAgreementDateFrom()).thenReturn(Date.valueOf("2026-05-03"));
        when(request.getAgreementDateTo()).thenReturn(Date.valueOf("2026-10-03"));
        when(request.getSelectedRisks()).thenReturn(List.of("MEDICAL RISK"));

        String errorCode = "ERROR_CODE_1";
        String description = "Field personFirstName is empty!";

        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationError(errorCode, description));
        ReflectionTestUtils.setField(validator, "errorFactory", errorFactory);

        Optional<ValidationError> error = validator.validate(request);
        assertTrue("",isEqual(error.get(), new ValidationError(errorCode, description)));
    }


}
