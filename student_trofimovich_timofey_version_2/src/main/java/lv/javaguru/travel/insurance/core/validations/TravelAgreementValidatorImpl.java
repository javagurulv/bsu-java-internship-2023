package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
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
class TravelAgreementValidatorImpl implements TravelAgreementValidator {
    @Autowired
    private List<TravelAgreementFieldValidation> agreementValidations;
    @Autowired
    private List<TravelPersonFieldValidation> personValidations;


    public List<ValidationErrorDTO> validate(AgreementDTO agreement) {
        List<ValidationErrorDTO> agreementErrors = getAgreementErrors(agreement);

        List<ValidationErrorDTO> personErrors = agreement.getPersons().stream()
                .map(this::getPersonErrors)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return concatLists(agreementErrors, personErrors);
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
    private List<ValidationErrorDTO> getSinglePersonErrors(PersonDTO person) {
        return personValidations.stream()
                .map(validation -> validation.validate(person))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
    private List<ValidationErrorDTO> getListPersonErrors(PersonDTO person) {
        return personValidations.stream()
                .map(validation -> validation.validateList(person))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList();
    }


    private List<ValidationErrorDTO> getAgreementErrors(AgreementDTO agreement) {
        List<ValidationErrorDTO> singleAgreementErrors = getSingleAgreementErrors(agreement);
        List<ValidationErrorDTO> listAgreementErrors = getListAgreementErrors(agreement);
        return concatLists(singleAgreementErrors, listAgreementErrors);
    }

    private List<ValidationErrorDTO> getPersonErrors(PersonDTO person) {
        List<ValidationErrorDTO> singleAgreementErrors = getSinglePersonErrors(person);
        List<ValidationErrorDTO> listAgreementErrors = getListPersonErrors(person);
        return concatLists(singleAgreementErrors, listAgreementErrors);
    }


    private List<ValidationErrorDTO> concatLists(List<ValidationErrorDTO> singleErrors,
                                                   List<ValidationErrorDTO> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }
}
