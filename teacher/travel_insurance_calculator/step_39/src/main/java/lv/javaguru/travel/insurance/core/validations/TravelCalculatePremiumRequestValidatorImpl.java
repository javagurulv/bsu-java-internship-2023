package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class TravelCalculatePremiumRequestValidatorImpl
    implements TravelCalculatePremiumRequestValidator {

    @Autowired
    private List<TravelRequestValidation> travelValidations;

    @Override
    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return travelValidations.stream()
                .map(validation -> validation.execute(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}
