package lv.javaguru.travel.insurance.core.validations;

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
class TravelPersonFieldValidator {

    @Autowired
    private List<TravelPersonFieldValidation> personFieldValidations;

    List<ValidationErrorDTO> validate(List<PersonDTO> persons) {
        return persons.stream()
                        .map(this::collectPersonErrors)
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> collectPersonErrors(PersonDTO person) {
        List<ValidationErrorDTO> singleErrors = collectSinglePersonErrors(person);
        List<ValidationErrorDTO> listErrors = collectListPersonErrors(person);
        return concatenateLists(singleErrors, listErrors);
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
