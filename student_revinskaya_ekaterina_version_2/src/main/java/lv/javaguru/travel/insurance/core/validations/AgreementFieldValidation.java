package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AgreementFieldValidation {
    @Autowired
    private List<TravelAgreementFieldValidation> agreementFieldValidations;

    public List<ValidationErrorDTO> validateErrors(AgreementDTO request) {
        List<ValidationErrorDTO> agreementSingleErrors = validateAgreementSingleErrors(request);
        List<ValidationErrorDTO> agreementListErrors = validateAgreementListErrors(request);
        return concatenateErrorLists(agreementSingleErrors, agreementListErrors);
    }

    private List<ValidationErrorDTO> validateAgreementSingleErrors(AgreementDTO request) {
        return agreementFieldValidations.stream()
                .map(validation -> validation.validate(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> validateAgreementListErrors(AgreementDTO request) {
        return agreementFieldValidations.stream()
                .map(validation -> validation.validateList(request))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> concatenateErrorLists(
            List<ValidationErrorDTO> singleErrors, List<ValidationErrorDTO> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }
}
