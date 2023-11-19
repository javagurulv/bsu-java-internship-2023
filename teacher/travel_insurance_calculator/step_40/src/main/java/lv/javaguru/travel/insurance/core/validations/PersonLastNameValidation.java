package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonLastNameValidation implements TravelRequestValidation {

    @Autowired private ErrorCodeUtil errorCodeUtil;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(buildError("ERROR_CODE_8"))
                : Optional.empty();
    }

    private ValidationError buildError(String errorCode) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationError(errorCode, errorDescription);
    }

}
