package lv.javaguru.travel.insurance.core.validationTests;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.validation.TravelRequestMedicalRiskLimitLevelNotEmptyValidation;
import lv.javaguru.travel.insurance.rest.validation.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelRequestMedicalRiskLimitLevelNotEmptyValidationTest {
    @InjectMocks
    private TravelRequestMedicalRiskLimitLevelNotEmptyValidation validator = new TravelRequestMedicalRiskLimitLevelNotEmptyValidation();

    @Mock
    private ValidationErrorFactory errorFactory = mock(ValidationErrorFactory.class);

    TravelCalculatePremiumRequest request;

    private String errorCode = "ERROR_CODE_14";
    private String description = "Field medicalRiskLimitLevel is empty!";

    @Test
    public void MedicalRiskLimitLevelNotEmptyValidationTest() {
        init("");
        ValidationError expectedError = new ValidationError(errorCode, description);
        assertTrue(isEqual(expectedError, validator.validate(request).get()));
    }

    @Test
    public void MedicalRiskLimitLevelNotNullValidationTest() {
        init(null);
        ValidationError expectedError = new ValidationError(errorCode, description);
        assertTrue(isEqual(expectedError, validator.validate(request).get()));
    }

    private boolean isEqual(ValidationError e1, ValidationError e2) {
        return e1.getErrorCode().equals(e2.getErrorCode())
                && e1.getDescription().equals(e2.getDescription());
    }
    private void init(String medicalRiskLimitLevelValue) {
        request = mock(TravelCalculatePremiumRequest.class);

        when(request.getMedicalRiskLimitLevel()).thenReturn(medicalRiskLimitLevelValue);

        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationError(errorCode, description));

        ReflectionTestUtils.setField(validator, "errorFactory", errorFactory);
    }
}
