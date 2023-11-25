package lv.javaguru.travel.insurance.core.validations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator{

    @Autowired
    List<TravelRequestValidation> requestValidations;

    @Override
    public List<ValidationError> validate (TravelCalculatePremiumRequest request) {
        return requestValidations.stream()
                .map(validator -> validator.validateArgs(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
