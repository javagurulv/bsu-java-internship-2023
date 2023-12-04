package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ListPersonFieldValidations {
    @Autowired
    private List<TravelPersonFieldValidation> personFieldValidations;

    public List<ValidationErrorDTO> validateErrors(AgreementDTO agreementDTO) {
        return agreementDTO.getPersons() != null
                ? agreementDTO.getPersons().stream()
                .flatMap(person -> validatePersonSingleAndList(agreementDTO, person).stream())
                .collect(Collectors.toList())
                : List.of();
    }

    private List<ValidationErrorDTO> validatePersonSingleAndList(AgreementDTO agreementDTO, PersonDTO personDTO) {
        List<ValidationErrorDTO> personSingleErrors = validatePersonSingleErrors(agreementDTO, personDTO);
        List<ValidationErrorDTO> personListErrors = validatePersonListErrors(agreementDTO, personDTO);
        return concatenateErrorLists(personSingleErrors, personListErrors);
    }

    private List<ValidationErrorDTO> validatePersonSingleErrors(AgreementDTO agreementDTO, PersonDTO person) {
        return personFieldValidations.stream()
                .map(validation -> validation.validate(agreementDTO, person))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> validatePersonListErrors(AgreementDTO agreementDTO, PersonDTO person) {
        return personFieldValidations.stream()
                .map(validation -> validation.validateList(agreementDTO, person))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDTO> concatenateErrorLists(
            List<ValidationErrorDTO> singleErrors, List<ValidationErrorDTO> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }

}
