package lv.javaguru.travel.insurance.core.validations;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator {
    @Autowired
    private List<TravelRequestValidation> validations;
@Override
    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        for (TravelRequestValidation validation : validations) {
            validation.validate(request).ifPresent(errors::add);
        }

        return errors;
    }

}