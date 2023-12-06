package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CountryValidation extends TravelRequestValidationImpl {
    @Autowired
    ValidationErrorFactory errorFactory;
    @Autowired
    ClassifierValueRepository classifierValueRepository;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
            if (countryIsEmpty(request)) {
                return Optional.of(errorFactory.buildError("ERROR_CODE_10"));
            }
            else if (!countryIsRecognised(request)) {
                return Optional.of(errorFactory.buildError("ERROR_CODE_11", List.of(new Placeholder("country", request.getCountry()))));
            }
        return Optional.empty();
    }
    private boolean countryIsEmpty(TravelCalculatePremiumRequest request) {
        return request.getCountry() == null || request.getCountry().isBlank();
    }

    private boolean countryIsRecognised(TravelCalculatePremiumRequest request) {
        String country = request.getCountry();
        return classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", country).isPresent();

    }
}