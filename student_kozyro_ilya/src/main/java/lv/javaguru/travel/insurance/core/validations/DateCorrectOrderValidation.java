package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DateCorrectOrderValidation implements TravelRequestValidation{
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        var from = travelCalculatePremiumRequest.getAgreementDateFrom();
        var to = travelCalculatePremiumRequest.getAgreementDateTo();

        return ((from != null && to != null) && (from.after(to) || from.equals(to))) ?
                Optional.of(new ValidationError("agreementDayFrom", "Must be before agreementDayTo")) :
                Optional.empty();

    }
}
