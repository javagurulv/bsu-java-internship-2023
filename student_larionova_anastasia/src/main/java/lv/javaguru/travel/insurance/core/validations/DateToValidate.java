package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DateToValidate implements TravelRequestValidator {

    @Autowired private ValidationErrorFactory validationError;

    @Override
    public Optional<ValidationError> validator(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(validationError.buildError("ERROR_CODE_5"))
                : Optional.empty();
    }
}
