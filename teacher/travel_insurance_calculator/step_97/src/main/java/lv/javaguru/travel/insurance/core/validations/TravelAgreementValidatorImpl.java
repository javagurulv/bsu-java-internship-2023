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

    @Autowired private List<TravelAgreementFieldValidation> agreementFieldValidations;
    @Autowired private List<TravelPersonFieldValidation> personFieldValidations;

    @Override
    public List<ValidationErrorDTO> validate(AgreementDTO agreement) {
        List<ValidationErrorDTO> agreementErrors = collectAgreementErrors(agreement);

        List<ValidationErrorDTO> personErrors =
                agreement.getPersons().stream()
                        .map(this::collectPersonErrors)
                        .flatMap(List::stream)
                        .collect(Collectors.toList());

        return concatenateLists(agreementErrors, personErrors);
    }

    private List<ValidationErrorDTO> collectAgreementErrors(AgreementDTO agreement) {
        List<ValidationErrorDTO> singleErrors = collectSingleAgreementErrors(agreement);
        List<ValidationErrorDTO> listErrors = collectListAgreementErrors(agreement);
        return concatenateLists(singleErrors, listErrors);
    }

    private List<ValidationErrorDTO> collectPersonErrors(PersonDTO person) {
        List<ValidationErrorDTO> singleErrors = collectSinglePersonErrors(person);
        List<ValidationErrorDTO> listErrors = collectListPersonErrors(person);
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

    private List<ValidationErrorDTO> collectSinglePersonErrors(PersonDTO person) {
        return personFieldValidations.stream()
                .map(validation -> validation.validate(person))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> collectListPersonErrors(PersonDTO person) {
        return personFieldValidations.stream()
                .map(validation -> validation.validateList(person))
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
