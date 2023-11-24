package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class TravelCalculatePremiumRequestValidatorImpl
    implements TravelCalculatePremiumRequestValidator {

    @Autowired private List<TravelAgreementFieldValidation> agreementFieldValidations;
    @Autowired private List<TravelPersonFieldValidation> personFieldValidations;

    @Override
    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> agreementErrors = collectAgreementErrors(request);
        List<ValidationError> personErrors = collectPersonErrors(request);
        return concatenateLists(agreementErrors, personErrors);
    }

    private List<ValidationError> collectAgreementErrors(TravelCalculatePremiumRequest request) {
        List<ValidationError> singleErrors = collectSingleAgreementErrors(request);
        List<ValidationError> listErrors = collectListAgreementErrors(request);
        return concatenateLists(singleErrors, listErrors);
    }

    private List<ValidationError> collectPersonErrors(TravelCalculatePremiumRequest request) {
        List<ValidationError> singleErrors = collectSinglePersonErrors(request);
        List<ValidationError> listErrors = collectListPersonErrors(request);
        return concatenateLists(singleErrors, listErrors);
    }

    private List<ValidationError> collectSingleAgreementErrors(TravelCalculatePremiumRequest request) {
        return agreementFieldValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationError> collectListAgreementErrors(TravelCalculatePremiumRequest request) {
        return agreementFieldValidations.stream()
                .map(validation -> validation.validateList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationError> collectSinglePersonErrors(TravelCalculatePremiumRequest request) {
        return personFieldValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationError> collectListPersonErrors(TravelCalculatePremiumRequest request) {
        return personFieldValidations.stream()
                .map(validation -> validation.validateList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


    private List<ValidationError> concatenateLists(List<ValidationError> singleErrors,
                                                   List<ValidationError> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }

}
