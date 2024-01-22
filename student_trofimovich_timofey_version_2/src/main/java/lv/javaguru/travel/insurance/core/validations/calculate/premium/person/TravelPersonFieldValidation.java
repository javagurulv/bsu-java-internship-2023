package lv.javaguru.travel.insurance.core.validations.calculate.premium.person;


import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface TravelPersonFieldValidation {
    Optional<ValidationErrorDTO> validate(PersonDTO person);
    List<ValidationErrorDTO> validateList(PersonDTO person);
}
