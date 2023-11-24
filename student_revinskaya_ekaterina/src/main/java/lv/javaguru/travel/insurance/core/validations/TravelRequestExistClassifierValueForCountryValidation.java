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
public class TravelRequestExistClassifierValueForCountryValidation
        extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return validateExistClassifierValue(request) ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_11",
                        List.of(new Placeholder("NOT_EXISTING_COUNTRY", request.getCountry()))))
                : Optional.empty();
    }
    private boolean validateExistClassifierValue(TravelCalculatePremiumRequest request){
        return request.getCountry() != null && !request.getCountry().isEmpty()
                && classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", request.getCountry()).isEmpty();
    }
}
