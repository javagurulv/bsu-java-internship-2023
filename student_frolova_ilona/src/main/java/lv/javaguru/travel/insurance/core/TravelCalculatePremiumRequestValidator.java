package lv.javaguru.travel.insurance.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lv.javaguru.travel.insurance.core.validations.TravelRequestValidation;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@Component
@Getter
public class TravelCalculatePremiumRequestValidator {

    @Autowired
    List<TravelRequestValidation> validations;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return validations.stream()
                .map(x -> x.check(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
