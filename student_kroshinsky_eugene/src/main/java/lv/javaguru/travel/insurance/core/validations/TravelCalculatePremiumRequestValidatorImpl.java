package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator{
    @Autowired List<TravelRequestValidation> validations;
    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        for (TravelRequestValidation validator : validations){
            validator.validate(request).ifPresent(errors::add);
        }
        return errors;
    }
}
