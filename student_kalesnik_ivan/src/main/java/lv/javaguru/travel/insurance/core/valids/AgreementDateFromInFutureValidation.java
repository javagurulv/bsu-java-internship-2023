package lv.javaguru.travel.insurance.core.valids;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class AgreementDateFromInFutureValidation implements TravelRequestValidation {

    @Autowired
    private DateTimeService dateTimeService;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date currentDateTime = dateTimeService.getCurrentDateTime();
        return (dateFrom != null && dateFrom.before(currentDateTime))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be in the future!"))
                : Optional.empty();
    }

}
