package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
class TravelRequestAgreementDateFromOfFutureValidation implements TravelRequestValidation {
    DateTimeService dateTimeService = new DateTimeService();
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        return (dateFrom != null && (dateTimeService.getCurrentDateTime().after(dateFrom)))
                ? Optional.of(new ValidationError("agreementDateFrom", "agreementDateFrom must be future date"))
                : Optional.empty();
    }
}
