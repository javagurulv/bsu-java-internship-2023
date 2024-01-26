package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class SelectedRisksIsEmptyValidation implements TravelRequestValidation {
    String errorCode = "ERROR_CODE_8";
    @Autowired private ErrorCodeUtil errorCodeUtil;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty())
                ? Optional.of(new ValidationError(errorCode, errorCodeUtil.getErrorDescription(errorCode)))
                : Optional.empty();
    }
}
