package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.TravelRequestValidation;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TravelCalculatePremiumRequestValidator {
    @Autowired
    List<TravelRequestValidation> validations;


    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validations.forEach(v -> v.validate(request).ifPresent(errors::add));
        return errors;
    }

    public TravelCalculatePremiumRequestValidator(List<TravelRequestValidation> validations) {
        this.validations = validations;
    }
}
