package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class AgreementDateDifferenceValidator extends TravelRequestValidationImpl {
    @Autowired private ValidationErrorFactory validationErrorFactory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (isDatesAreNull(request) && isDateToBeforeDateFrom(request))
                ? Optional.of(validationErrorFactory.createValidationError("ERROR_CODE_1"))
                : Optional.empty();
    }
    private Boolean isDateToBeforeDateFrom(TravelCalculatePremiumRequest request) {
        return request.getAgreementDateTo().before(request.getAgreementDateFrom());
    }

    private Boolean isDatesAreNull(TravelCalculatePremiumRequest request) {
        return request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null;
    }
}
