package lv.javaguru.travel.insurance.core.validations.calculate.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.calculate.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.calculate.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.core.validations.calculate.agreement.TravelAgreementFieldValidationImpl;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractAgreementValidationTest {
    protected ValidationErrorFactory errorFactory;

    protected TravelAgreementFieldValidationImpl validation;

    protected ClassifierValueRepository classifierValueRepository;
    protected AgreementDTO agreement;

    protected String errorCode;
    protected String description;

    protected ClassifierValue classifierValue;
    void baseInit(String errorCode_, String description_, TravelAgreementFieldValidationImpl validationValue) {
        validation = validationValue;
        errorCode = errorCode_;
        description = description_;
        errorFactory = mock(ValidationErrorFactory.class);
        agreement = mock(AgreementDTO.class);
        when(errorFactory.buildError(errorCode_)).thenReturn(new ValidationErrorDTO(errorCode_, description_));
        ReflectionTestUtils.setField(validation, "errorFactory", errorFactory);
    }

    void initClassifierValue(String classifierIc, String ic) {
        classifierValue = mock(ClassifierValue.class);

        classifierValueRepository = mock(ClassifierValueRepository.class);
        when(classifierValueRepository.findByClassifierTitleAndIc(classifierIc, ic)).thenReturn(Optional.empty());
        ReflectionTestUtils.setField(validation, "classifierValueRepository", classifierValueRepository);
    }
    void initPlaceholder(Placeholder expectedPlaceholder) {
        when(errorFactory.buildError(errorCode, List.of(expectedPlaceholder))).thenReturn(new ValidationErrorDTO(errorCode, description));
    }

    void initAll(String errorCode_, String description_, TravelAgreementFieldValidationImpl validationValue, String classifierIc, String ic, Placeholder placeholder) {
        baseInit(errorCode_, description_, validationValue);
        initClassifierValue(classifierIc, ic);
        //initPlaceholder(placeholder);
    }
}
