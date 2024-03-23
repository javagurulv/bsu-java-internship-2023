package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class ValidateDateToIsInTheFuture extends TravelRequestValidationImpl {
    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Autowired
    private ValidationErrorFactory factory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date todayDateAndTime = dateTimeUtil.getTodaysDateAndTime();
        Date agreementDateTo = request.getAgreementDateTo();
        return (agreementDateTo != null && agreementDateTo.before(todayDateAndTime))
                ? Optional.of(factory.createError("ERROR_CODE_4"))
                : Optional.empty();
    }
}
