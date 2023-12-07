package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.services.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.Placer;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static lv.javaguru.travel.insurance.core.validations.errors.ValidationErrorCodes.MISSING_MANDATORY_FIELD;

@Component
public class AgreementDateFromValidation implements TravelRequestValidation{
    @Autowired
    ValidationErrorFactory validationErrorFactory;
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null) ?
                Optional.of(new ValidationError("agreementDateFrom", "Shouldn't be empty!")) :
                Optional.empty();
    }
}
