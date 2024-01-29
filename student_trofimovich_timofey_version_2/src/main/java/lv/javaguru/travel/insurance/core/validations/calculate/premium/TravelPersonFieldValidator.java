package lv.javaguru.travel.insurance.core.validations.calculate.premium;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.calculate.premium.person.TravelPersonFieldValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class TravelPersonFieldValidator {
    @Autowired
    private List<TravelPersonFieldValidation> personValidations;

    public List<ValidationErrorDTO> validate(AgreementDTO agreement) {

        return agreement.getPersons().stream()
                .map(person -> getPersonErrors(person, agreement))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> getPersonErrors(PersonDTO person, AgreementDTO agreement) {
        List<ValidationErrorDTO> singlePersonErrors = getSinglePersonErrors(person, agreement);
        List<ValidationErrorDTO> listPersonErrors = getListPersonErrors(person, agreement);
        return concatLists(singlePersonErrors, listPersonErrors);
    }

    private List<ValidationErrorDTO> getSinglePersonErrors(PersonDTO person, AgreementDTO agreement) {
        return personValidations.stream()
                .map(validation -> validation.validate(person, agreement))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
    private List<ValidationErrorDTO> getListPersonErrors(PersonDTO person, AgreementDTO agreement) {
        return personValidations.stream()
                .map(validation -> validation.validateList(person, agreement))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toList();
    }

    private List<ValidationErrorDTO> concatLists(List<ValidationErrorDTO> singleErrors,
                                                 List<ValidationErrorDTO> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }

}
