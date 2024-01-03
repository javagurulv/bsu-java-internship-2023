package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.core.validations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class TravelCalculatePremiumRequestValidator {

    @Autowired private List<TravelRequestValidator> travelValidations;
    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        for (var validation : travelValidations) {
            Optional<ValidationError> error = validation.validator(request);
            if (error.isPresent()) {
                errors.add(error.get());
            }
        }
        return errors;
    }
}