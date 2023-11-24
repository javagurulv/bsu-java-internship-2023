package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class AgreementDateFromIsNotInPastValidator implements TravelRequestValidation{
    @Override
    public Optional<ValidationError> validateArgs(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date currentDate = new Date();
        return (dateFrom != null && dateFrom.getTime() < currentDate.getTime())
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be in past!"))
                : Optional.empty();
    }
}
