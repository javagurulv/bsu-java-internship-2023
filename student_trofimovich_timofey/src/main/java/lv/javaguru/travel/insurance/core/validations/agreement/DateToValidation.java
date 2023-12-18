package lv.javaguru.travel.insurance.core.validations.agreement;


import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class DateToValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    ValidationErrorFactory factory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(factory.buildError("ERROR_CODE_4"))
                : Optional.empty();
    }
}
