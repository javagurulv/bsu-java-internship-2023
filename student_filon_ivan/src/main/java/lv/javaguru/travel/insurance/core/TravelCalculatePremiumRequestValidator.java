package lv.javaguru.travel.insurance.core;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.validator.TravelRequestValidation;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;


@Component
@Setter
@AllArgsConstructor
class TravelCalculatePremiumRequestValidator {

    @Autowired
    private List<TravelRequestValidation> validations;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request){
        return validations.stream()
                .map(validations -> validations.execute(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
