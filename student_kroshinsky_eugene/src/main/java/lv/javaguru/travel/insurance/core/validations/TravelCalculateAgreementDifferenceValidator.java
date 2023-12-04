package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class TravelCalculateAgreementDifferenceValidator implements TravelRequestValidation {
    @Autowired private PropertyReader propertyReader;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null
                && request.getAgreementDateTo().before(request.getAgreementDateFrom())
        )
                ? Optional.of(createValidationError("ERROR_CODE_1"))
                : Optional.empty();
    }
    private ValidationError createValidationError(String errorCode) {
        return new ValidationError(errorCode, propertyReader.getProperty(errorCode));
    }
}
