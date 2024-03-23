package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TravelRequestPersonIcValidation extends TravelRequestValidationImpl{
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        if (request.getPersonIc() == null || request.getPersonIc().isEmpty()) {
            String errorCode = "ERROR_CODE_16";
            return Optional.of(errorFactory.buildError(errorCode));
        }

        return Optional.empty();
    }
}
