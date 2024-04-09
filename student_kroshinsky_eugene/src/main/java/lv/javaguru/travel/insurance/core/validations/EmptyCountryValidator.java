package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class EmptyCountryValidator extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (isCountryEmpty(request))
                ? Optional.of(validationErrorFactory.createValidationError("ERROR_CODE_8"))
                : Optional.empty();
    }
    private Boolean isCountryEmpty(TravelCalculatePremiumRequestV1 request) {
        return request.getCountry() == null || request.getCountry().isBlank();
    }
}
