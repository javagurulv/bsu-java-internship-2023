package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class PremiumRequestValidatorImp implements PremiumRequestValidator {
    @Autowired List<TravelRequestValidation> validations;
    public List<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        List<ValidationError> errors = new ArrayList<>();
        for (TravelRequestValidation validator : validations){
            validator.validate(request).ifPresent(errors::add);
            List<ValidationError> validationErrors = validator.validateList(request);
            if (!validationErrors.isEmpty()) {
                errors.addAll(validationErrors);
            }
        }
        return errors;
    }
}
