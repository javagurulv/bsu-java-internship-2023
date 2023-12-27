package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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


    public List<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        List<ValidationError> agreementErrors = getAgreementErrors(request);
        List<ValidationError> personErrors = getPersonErrors(request);
        return concatLists(agreementErrors, personErrors);
    }

    private List<ValidationError> getSingleAgreementErrors(TravelCalculatePremiumRequestV1 request) {
        return agreementValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private List<ValidationError> getListAgreementErrors(TravelCalculatePremiumRequestV1 request) {
        return agreementValidations.stream()
                .map(validation -> validation.validateList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList();
    }
    private List<ValidationError> getSinglePersonErrors(TravelCalculatePremiumRequestV1 request) {
        return personValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
    private List<ValidationError> getListPersonErrors(TravelCalculatePremiumRequestV1 request) {
        return personValidations.stream()
                .map(validation -> validation.validateList(request))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList();
    }


    private List<ValidationError> getAgreementErrors(TravelCalculatePremiumRequestV1 request) {
        List<ValidationError> singleAgreementErrors = getSingleAgreementErrors(request);
        List<ValidationError> listAgreementErrors = getListAgreementErrors(request);
        return concatLists(singleAgreementErrors, listAgreementErrors);
    }

    private List<ValidationError> getPersonErrors(TravelCalculatePremiumRequestV1 request) {
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
