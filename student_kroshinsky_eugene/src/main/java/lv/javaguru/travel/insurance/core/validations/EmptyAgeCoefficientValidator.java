package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.utils.AgeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class EmptyAgeCoefficientValidator extends TravelRequestValidationImpl {
    @Autowired private ValidationErrorFactory validationErrorFactory;
    @Autowired private AgeUtil ageUtil;
    @Value("${age.coefficient.enabled:false}")
    private boolean ageCoefficientEnabled;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (ageCoefficientEnabled && request.getPersonBirthDate() != null && checkAge(request))
                ? Optional.of(validationErrorFactory.createValidationError("ERROR_CODE_11"))
                : Optional.empty();
    }
    private boolean checkAge(TravelCalculatePremiumRequest request) {
        int age = ageUtil.calculateAge(request);
        return age < 0 || age > 150;
    }
}
