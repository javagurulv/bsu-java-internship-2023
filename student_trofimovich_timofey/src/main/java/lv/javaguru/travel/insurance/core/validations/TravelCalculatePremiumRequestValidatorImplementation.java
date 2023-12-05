package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
class TravelCalculatePremiumRequestValidatorImplementation implements TravelCalculatePremiumRequestValidator {
    @Autowired
    private List<TravelRequestValidation> validations;


    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> singleErrorsList = getSingleErrorsList(request);
        List<ValidationError> selectedRisksErrorsList = getSelectedRisksErrorsList(request);
        return Stream.concat(singleErrorsList.stream(), selectedRisksErrorsList.stream())
                .toList();
    }

    private List<ValidationError> getSingleErrorsList(TravelCalculatePremiumRequest request) {
        return validations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private List<ValidationError> getSelectedRisksErrorsList(TravelCalculatePremiumRequest request) {
        return validations.stream()
                .map(validation -> validation.validateList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList();
    }

    public TravelCalculatePremiumRequestValidatorImplementation(List<TravelRequestValidation> validations) {
        this.validations = validations;
    }
}
