package lv.javaguru.travel.insurance.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lv.javaguru.travel.insurance.core.validation.TravelRequestValidation;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestValidator {

    @Autowired
    List<TravelRequestValidation> travelRequestValidations;

    public List<ValidationError> validate (TravelCalculatePremiumRequest request) {
        return travelRequestValidations.stream()
                .map(validator -> validator.validateArg(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
