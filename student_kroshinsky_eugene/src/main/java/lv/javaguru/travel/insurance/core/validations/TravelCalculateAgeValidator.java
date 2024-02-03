package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.utils.AgeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class TravelCalculateAgeValidator extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired
    private AgeUtil ageUtil;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getDateOfBirth() != null  &&
                (ageUtil.calculateAge(request) < 0 || ageUtil.calculateAge(request) > 150))
                ? Optional.of(validationErrorFactory.createValidationError("ERROR_CODE_11"))
                : Optional.empty();
    }
}
