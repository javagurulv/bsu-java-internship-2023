package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PersonValidator {

    @Autowired private List<PersonFieldValidation> personFieldValidations;
    public List<ValidationErrorDto> validate(AgreementDto agreement) {
        return agreement.getPersons().stream()
                .map(person -> collectPersonErrors(person, agreement))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
    private List<ValidationErrorDto> collectPersonErrors(PersonDto person, AgreementDto agreement) {
        List<ValidationErrorDto> singleErrors = collectSinglePersonErrors(person, agreement);
        List<ValidationErrorDto> listErrors = collectListPersonErrors(person, agreement);
        return concatenateLists(singleErrors, listErrors);
    }



    private List<ValidationErrorDto> collectSinglePersonErrors(PersonDto person, AgreementDto agreement) {
        return personFieldValidations.stream()
                .map(validation -> validation.validate(person, agreement))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDto> collectListPersonErrors(PersonDto person, AgreementDto agreement) {
        return personFieldValidations.stream()
                .map(validation -> validation.validateList(person, agreement))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    protected List<ValidationErrorDto> concatenateLists(List<ValidationErrorDto> singleErrors,
                                                        List<ValidationErrorDto> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }
}
