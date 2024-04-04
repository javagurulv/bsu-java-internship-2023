package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class FutureDateOfBirthValidator extends TravelRequestValidationImpl{
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (request.getPersonBirthDate() != null && isDateOfBirthAfterCurrentDate(request))
                ? Optional.of(validationErrorFactory.createValidationError("ERROR_CODE_10"))
                : Optional.empty();
    }
    private Boolean isDateOfBirthAfterCurrentDate(TravelCalculatePremiumRequestV1 request) {
        return request.getPersonBirthDate().after(new Date());
    }
}
