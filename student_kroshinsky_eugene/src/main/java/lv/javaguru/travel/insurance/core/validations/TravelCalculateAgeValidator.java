package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.utils.AgeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class TravelCalculateAgeValidator extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired
    private AgeUtil ageUtil;
    @Value("${age.coefficient.enabled:false}")
    private boolean ageCoefficientEnabled;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (ageCoefficientEnabled && request.getDateOfBirth() != null && checkAge(request))
                ? Optional.of(validationErrorFactory.createValidationError("ERROR_CODE_11"))
                : Optional.empty();
    }

    private boolean checkAge(TravelCalculatePremiumRequest request) {
        return ageUtil.calculateAge(request) < 0 || ageUtil.calculateAge(request) > 150;
    }
}
