package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class DateToIsInFutureValidation implements TravelRequestValidation {
    @Autowired
    ErrorCodeUtil errorCodeUtil;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date dateTo = request.getAgreementDateTo();
        return (dateTo != null && dateTo.before(new Date()))
                ? Optional.of(new ValidationError("ERROR_CODE_6", errorCodeUtil.getErrorDescription("ERROR_CODE_6")))
                : Optional.empty();
    }
}
