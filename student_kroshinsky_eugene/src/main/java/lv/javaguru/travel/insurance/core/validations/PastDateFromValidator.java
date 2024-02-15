package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class PastDateFromValidator extends TravelRequestValidationImpl {
    @Autowired private ValidationErrorFactory validationErrorFactory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() != null && isDateFromBeforeCurrentDate(request))
                ? Optional.of(validationErrorFactory.createValidationError("ERROR_CODE_2"))
                : Optional.empty();
    }
    private Boolean isDateFromBeforeCurrentDate(TravelCalculatePremiumRequest request) {
        return request.getAgreementDateFrom().before(new Date());
    }
}
