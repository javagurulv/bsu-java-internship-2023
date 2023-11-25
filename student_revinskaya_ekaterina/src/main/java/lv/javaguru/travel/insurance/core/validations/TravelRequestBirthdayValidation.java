package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class TravelRequestBirthdayValidation extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return request.getBirthday() == null
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_12"))
                : Optional.empty();
    }
}
