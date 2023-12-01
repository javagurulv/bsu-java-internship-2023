package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class TravelAgreementFieldValidator {

    @Autowired
    private List<TravelAgreementFieldValidation> agreementFieldValidations;

    List<ValidationErrorDTO> validate(AgreementDTO agreement) {
        List<ValidationErrorDTO> singleErrors = collectSingleAgreementErrors(agreement);
        List<ValidationErrorDTO> listErrors = collectListAgreementErrors(agreement);
        return concatenateLists(singleErrors, listErrors);
    }

    private List<ValidationErrorDTO> collectSingleAgreementErrors(AgreementDTO agreement) {
        return agreementFieldValidations.stream()
                .map(validation -> validation.validate(agreement))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> collectListAgreementErrors(AgreementDTO agreement) {
        return agreementFieldValidations.stream()
                .map(validation -> validation.validateList(agreement))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> concatenateLists(List<ValidationErrorDTO> singleErrors,
                                                      List<ValidationErrorDTO> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }

}
