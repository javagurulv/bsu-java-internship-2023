package lv.javaguru.travel.insurance.core.validations.calculate;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TravelOnlyPersonValidatorImpl implements TravelOnlyPersonValidator {
    @Autowired
    List<TravelPersonFieldValidation> personValidations;

    @Override
    public List<ValidationErrorDTO> validate(AgreementDTO agreement) {
        List<ValidationErrorDTO> result = new ArrayList<>();
        agreement.getPersons().forEach(person -> {
            personValidations.forEach(validation -> {
                Optional<ValidationErrorDTO> error = validation.validate(person);
                error.ifPresent(result::add);
            });
        });

        return result;
    }
}
