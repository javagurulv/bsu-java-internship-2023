package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        List<TravelRequestValidation> travelRequestValidations = createValidations();

        for (TravelRequestValidation validation : travelRequestValidations) {
            Optional<ValidationError> optionalValidationError = validation.execute(request);
            if (optionalValidationError.isPresent()) {
                errors.add(optionalValidationError.get());
            }
        }
        return errors;
    }

    private List<TravelRequestValidation> createValidations() {
        List<TravelRequestValidation> travelRequestValidations = new ArrayList<>();

        travelRequestValidations.add(new ValidatePersonFirstName());
        travelRequestValidations.add(new ValidatePersonLastName());
        travelRequestValidations.add(new ValidatePersonDateFrom());
        travelRequestValidations.add(new ValidatePersonDateTo());
        travelRequestValidations.add(new ValidationDateFromInThePast());
        travelRequestValidations.add(new ValidationDateToInThePast());
        travelRequestValidations.add(new ValidateDateToWithDateFrom());

        return travelRequestValidations;
    }

}

