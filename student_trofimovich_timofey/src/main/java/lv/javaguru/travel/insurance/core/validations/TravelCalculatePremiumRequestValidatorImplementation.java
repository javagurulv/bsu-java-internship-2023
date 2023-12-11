package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class TravelCalculatePremiumRequestValidatorImplementation implements TravelCalculatePremiumRequestValidator {
    @Autowired
    private List<TravelAgreementFieldValidation> agreementValidations;
    @Autowired
    private List<TravelPersonFieldValidation> personValidations;


    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> agreementErrors = getAgreementErrors(request);
        List<ValidationError> personErrors = getPersonErrors(request);
        return concatLists(agreementErrors, personErrors);
    }

    private List<ValidationError> getSingleAgreementErrors(TravelCalculatePremiumRequest request) {
        return agreementValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private List<ValidationError> getListAgreementErrors(TravelCalculatePremiumRequest request) {
        return agreementValidations.stream()
                .map(validation -> validation.validateList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList();
    }
    private List<ValidationError> getSinglePersonErrors(TravelCalculatePremiumRequest request) {
        return personValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
    private List<ValidationError> getListPersonErrors(TravelCalculatePremiumRequest request) {
        return personValidations.stream()
                .map(validation -> validation.validateList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList();
    }


    private List<ValidationError> getAgreementErrors(TravelCalculatePremiumRequest request) {
        List<ValidationError> singleAgreementErrors = getSingleAgreementErrors(request);
        List<ValidationError> listAgreementErrors = getListAgreementErrors(request);
        return concatLists(singleAgreementErrors, listAgreementErrors);
    }

    private List<ValidationError> getPersonErrors(TravelCalculatePremiumRequest request) {
        List<ValidationError> singleAgreementErrors = getSinglePersonErrors(request);
        List<ValidationError> listAgreementErrors = getListPersonErrors(request);
        return concatLists(singleAgreementErrors, listAgreementErrors);
    }


    private List<ValidationError> concatLists(List<ValidationError> singleErrors,
                                                   List<ValidationError> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }
}
