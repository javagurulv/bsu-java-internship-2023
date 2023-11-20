package lv.javaguru.travel.insurance.core.valids;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.core.ErrorCodeValueUtil;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.javaguru.travel.insurance.core.valids.ValidationErrorFactory;

import java.util.Date;
import java.util.Optional;

@Component
public
class AgreementDateFromInFutureValidation implements TravelRequestValidation {

    @Autowired
    private DateTimeService dateTimeService;

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date currentDateTime = dateTimeService.getCurrentDateTime();
        return (dateFrom != null && dateFrom.before(currentDateTime))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_1"))
                : Optional.empty();
    }
}
