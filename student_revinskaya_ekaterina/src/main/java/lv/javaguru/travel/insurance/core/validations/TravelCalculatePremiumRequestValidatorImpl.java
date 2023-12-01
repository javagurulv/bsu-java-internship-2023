package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator {

    @Autowired
    private List<TravelRequestValidation> travelRequestValidations;

    @Override
    public List<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {

        List<ValidationError> singleErrors = validateSingleErrors(request);
        List<ValidationError> listErrors = validateListErrors(request);

        return concatenateErrorLists(singleErrors, listErrors);
    }

    private List<ValidationError> validateSingleErrors(TravelCalculatePremiumRequestV1 request) {
        return travelRequestValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationError> validateListErrors(TravelCalculatePremiumRequestV1 request) {
        return travelRequestValidations.stream()
                .map(validation -> validation.validateList(request))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationError> concatenateErrorLists(List<ValidationError> singleErrors,
                                                        List<ValidationError> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }


}
