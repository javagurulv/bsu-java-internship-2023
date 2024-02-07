package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class PremiumRequestValidatorImp implements PremiumRequestValidator {
    @Autowired List<TravelRequestValidation> validations;
    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return validations.stream().flatMap(validator -> Stream.concat(
                validator.validateList(request).stream(),
                validator.validate(request).stream()
        )).collect(Collectors.toList());
    }
}
