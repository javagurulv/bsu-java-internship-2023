package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.person.TravelPersonFieldValidation;
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
                .map(this::getPersonErrors)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> getPersonErrors(PersonDTO person) {
        List<ValidationErrorDTO> singlePersonErrors = getSinglePersonErrors(person);
        List<ValidationErrorDTO> listPersonErrors = getListPersonErrors(person);
        return concatLists(singlePersonErrors, listPersonErrors);
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

    private List<ValidationErrorDTO> concatLists(List<ValidationErrorDTO> singleErrors,
                                                 List<ValidationErrorDTO> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }

}
