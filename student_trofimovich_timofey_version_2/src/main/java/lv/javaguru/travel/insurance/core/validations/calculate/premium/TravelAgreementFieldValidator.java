package lv.javaguru.travel.insurance.core.validations.calculate.premium;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.calculate.premium.agreement.TravelAgreementFieldValidation;
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
    private List<TravelAgreementFieldValidation> agreementValidations;


    public List<ValidationErrorDTO> validate(AgreementDTO agreement) {

        return getAgreementErrors(agreement);
    }


    private List<ValidationErrorDTO> getSingleAgreementErrors(AgreementDTO agreement) {
        return agreementValidations.stream()
                .map(validation -> validation.validate(agreement))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private List<ValidationErrorDTO> getListAgreementErrors(AgreementDTO agreement) {
        return agreementValidations.stream()
                .map(validation -> validation.validateList(agreement))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList();
    }


    private List<ValidationErrorDTO> getAgreementErrors(AgreementDTO agreement) {
        List<ValidationErrorDTO> singleAgreementErrors = getSingleAgreementErrors(agreement);
        List<ValidationErrorDTO> listAgreementErrors = getListAgreementErrors(agreement);
        return concatLists(singleAgreementErrors, listAgreementErrors);
    }

    private List<ValidationErrorDTO> concatLists(List<ValidationErrorDTO> singleErrors,
                                                 List<ValidationErrorDTO> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }
}
