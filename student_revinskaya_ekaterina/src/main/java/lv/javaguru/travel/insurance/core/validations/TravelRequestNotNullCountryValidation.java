package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class TravelRequestNotNullCountryValidation extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired private ClassifierValueRepository classifierValueRepository;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {//can be validateList later
        return validateNotNull(request) ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_10",
                List.of(new Placeholder("SELECTED_RISK", "TRAVEL_MEDICAL"))))
                : Optional.empty();
    }
    private boolean validateNotNull(TravelCalculatePremiumRequestV1 request){
        return (request.getCountry() == null || request.getCountry().isEmpty());
    }
}
