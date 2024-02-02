package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TravelCalculateCountryValidator extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired
    private ClassifierValueRepository valueRepository;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        if (valueRepository.findByClassifierTitleAndIc("COUNTRY", request.getCountry()).isEmpty()
                && !request.getCountry().isBlank()) {
            Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK", request.getCountry());
            return Optional.of(validationErrorFactory.buildError("NOT_EXISTING_RISK", List.of(placeholder)));
        }
        return Optional.empty();
    }
}

