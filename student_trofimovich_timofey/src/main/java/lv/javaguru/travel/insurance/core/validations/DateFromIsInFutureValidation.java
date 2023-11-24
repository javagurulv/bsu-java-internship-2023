package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class DateFromIsInFutureValidation implements TravelRequestValidation {
    @Autowired
    ErrorCodeUtil errorCodeUtil;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        return (dateFrom != null && dateFrom.before(new Date()))
                ? Optional.of(new ValidationError("ERROR_CODE_5", errorCodeUtil.getErrorDescription("ERROR_CODE_5")))
                : Optional.empty();
    }
}
