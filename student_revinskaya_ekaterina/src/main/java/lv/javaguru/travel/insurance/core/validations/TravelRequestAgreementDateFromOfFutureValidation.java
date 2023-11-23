package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
class TravelRequestAgreementDateFromOfFutureValidation extends TravelRequestValidationImpl {
    @Autowired
    private DateTimeUtil dateTimeUtil;
@Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        return (dateFrom != null && (dateTimeUtil.getCurrentDateTime().after(dateFrom)))
                ? Optional.of( validationErrorFactory.constructError("ERROR_CODE_5"))
                : Optional.empty();
    }
}
