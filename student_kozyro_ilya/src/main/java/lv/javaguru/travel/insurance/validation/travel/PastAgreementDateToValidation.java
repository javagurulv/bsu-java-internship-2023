package lv.javaguru.travel.insurance.validation.travel;

import lv.javaguru.travel.insurance.core.services.DateService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PastAgreementDateToValidation implements TravelRequestValidation {

    @Autowired
    DateService dateService;

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        var to = travelCalculatePremiumRequest.getAgreementDateTo();

        return (to != null) ?
                to.after(dateService.getTodayDate()) ?
                        Optional.empty() :
                        Optional.of(new ValidationError("agreementDateTo", "Should be in a future, not in a past!")) :
                Optional.empty();

    }
}
