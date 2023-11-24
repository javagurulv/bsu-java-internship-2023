package lv.javaguru.travel.insurance.core.validations;


//import lv.javaguru.travel.insurance.dto.ValidationError;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
//import lv.javaguru.travel.insurance.rest.TravelRequestValidation;
import org.springframework.stereotype.Component;

import java.util.*;

@NoArgsConstructor
@Component
class TravelCalculatePremiumRequestValidator implements RequestValidator{
    @Override
    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<TravelRequestValidation> validations = new ArrayList<>();

        List<ValidationError> errors = new ArrayList<>();
        /*
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateTo(request).ifPresent(errors::add);
        */
        validations.add(new TravelRequestFirstNameValidation());
        validations.add(new TravelRequestLastNameValidation());
        validations.add(new TravelRequestDateFromValidation());
        validations.add(new TravelRequestDateToValidation());

        validations.forEach(validation -> {
            Optional<ValidationError> error = validation.validate(request);
            if (!error.isEmpty()) {
                errors.add(error.get());
            }
        });
        return errors;
    }
}