package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class AgreementDateDifferenceValidator extends TravelRequestValidationImpl {
    @Autowired private ValidationErrorFactory validationErrorFactory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (isDatesAreNull(request) && isDateToBeforeDateFrom(request))
                ? Optional.of(validationErrorFactory.createValidationError("ERROR_CODE_1"))
                : Optional.empty();
    }
    private Boolean isDateToBeforeDateFrom(TravelCalculatePremiumRequestV1 request) {
        return request.getAgreementDateTo().before(request.getAgreementDateFrom());
    }

    private Boolean isDatesAreNull(TravelCalculatePremiumRequestV1 request) {
        return request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null;
    }
}
