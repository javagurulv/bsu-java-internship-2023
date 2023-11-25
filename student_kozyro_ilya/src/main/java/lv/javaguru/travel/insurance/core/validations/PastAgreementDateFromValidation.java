package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.services.DateService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PastAgreementDateFromValidation implements TravelRequestValidation{

    @Autowired
    DateService dateService;
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        var from = request.getAgreementDateFrom();

        return (from != null) ?
                from.after(dateService.getTodayDate()) ?
                        Optional.empty() :
                        Optional.of(new ValidationError("agreementDateFrom", "Should be in a future, not in a past!")) :
                Optional.empty();
    }
}
