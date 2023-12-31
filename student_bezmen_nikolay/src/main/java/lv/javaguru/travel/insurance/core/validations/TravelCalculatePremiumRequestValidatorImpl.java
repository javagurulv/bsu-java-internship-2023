package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator{
    @Autowired private List<TravelRequestValidation> requestValidationList;

    public TravelCalculatePremiumRequestValidatorImpl(List<TravelRequestValidation> requestValidationList) {
        this.requestValidationList = requestValidationList;
    }

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return requestValidationList.stream()
                .map(validation -> validation.execute(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
