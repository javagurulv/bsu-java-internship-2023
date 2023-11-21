package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class TravelRequestSelectedRisksValidation implements TravelRequestValidation {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getSelected_risks() == null || request.getSelected_risks().isEmpty())
                ? Optional.of(validationErrorFactory.constructError("ERROR_CODE_8"))
                : Optional.empty();
    }

}
