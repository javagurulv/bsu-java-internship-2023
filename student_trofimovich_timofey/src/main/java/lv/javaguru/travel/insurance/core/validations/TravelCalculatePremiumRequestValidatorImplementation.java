package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class TravelCalculatePremiumRequestValidatorImplementation implements TravelCalculatePremiumRequestValidator {
    @Autowired
    private List<TravelRequestValidation> validations;


    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validations.forEach(v -> {
            v.validate(request).ifPresent(errors::add);
            List<ValidationError> selectedRisksErrorsList = v.validateList(request);
            errors.addAll(selectedRisksErrorsList);
        });
        return errors;
    }

    public TravelCalculatePremiumRequestValidatorImplementation(List<TravelRequestValidation> validations) {
        this.validations = validations;
    }
}
