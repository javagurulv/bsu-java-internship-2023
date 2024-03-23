package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator {
    @Autowired
    private List<TravelRequestValidation> travelValidations;
    private List<ValidationError> collectSingleErrors(TravelCalculatePremiumRequest request) {
        return travelValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
    private List<ValidationError> collectListErrors(TravelCalculatePremiumRequest request) {
        return travelValidations.stream()
                .map(validation -> validation.validateList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
    @Override
    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> singleErrors = collectSingleErrors(request);
        List<ValidationError> listErrors = collectListErrors(request);
//        List<ValidationError> allErrors = new ArrayList<>(singleErrors);
//        allErrors.addAll(listErrors);
        return Stream.concat(singleErrors.stream(),listErrors.stream()).collect(Collectors.toList());
    }
}
