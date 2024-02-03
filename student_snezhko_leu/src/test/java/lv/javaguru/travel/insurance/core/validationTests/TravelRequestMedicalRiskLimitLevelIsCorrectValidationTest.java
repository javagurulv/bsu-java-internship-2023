package lv.javaguru.travel.insurance.core.validationTests;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.rest.placeholder.Placeholder;
import lv.javaguru.travel.insurance.rest.validation.TravelRequestMedicalRiskLimitLevelIsCorrectValidation;
import lv.javaguru.travel.insurance.rest.validation.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelRequestMedicalRiskLimitLevelIsCorrectValidationTest {
    @InjectMocks
    private TravelRequestMedicalRiskLimitLevelIsCorrectValidation validator = new TravelRequestMedicalRiskLimitLevelIsCorrectValidation();

    @Mock
    private ValidationErrorFactory errorFactory = mock(ValidationErrorFactory.class);

    @Mock
    private ClassifierValueRepository cvRepository = mock(ClassifierValueRepository.class);

    private TravelCalculatePremiumRequestV1 request;

    private String errorCode;
    private String description;


    @Test
    public void MedicalRiskLimitLevelNotCorrectValidationTest() {
        init("INCORRECT_LIMIT_VALUE");
        ValidationError expectedError = new ValidationError(errorCode, description);
        assertTrue(isEquals(expectedError, validator.validate(request).get()));
    }
    private void init(String incorrectLimitValue) {
        errorCode = "ERROR_CODE_15";
        description = "Medical risk limit level "+ incorrectLimitValue + " is not supported!";
        request = mock(TravelCalculatePremiumRequestV1.class);
        when(request.getMedicalRiskLimitLevel()).thenReturn(incorrectLimitValue);

        Placeholder placeholder = new Placeholder("{NOT_EXISTING_LIMIT}", incorrectLimitValue);
        List<Placeholder> placeholderList = new ArrayList<>();
        placeholderList.add(placeholder);

        when(errorFactory.buildError(errorCode, placeholderList)).thenReturn(new ValidationError(errorCode, description));
        when(cvRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", incorrectLimitValue)).thenReturn(Optional.empty());

        ReflectionTestUtils.setField(validator, "placeholders", placeholderList);
        ReflectionTestUtils.setField(validator, "errorFactory", errorFactory);
        ReflectionTestUtils.setField(validator, "cvRepository", cvRepository);
    }

    private boolean isEquals(ValidationError e1, ValidationError e2) {
        return e1.getErrorCode().equals(e2.getErrorCode())
                && e1.getDescription().equals(e2.getDescription());
    }
}
