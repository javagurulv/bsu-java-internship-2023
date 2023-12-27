package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.DateServiceUtil;
import lv.javaguru.travel.insurance.core.services.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static lv.javaguru.travel.insurance.core.validations.errors.ValidationErrorCodes.PAST_DATE;

@Component
public class PastAgreementDateFromValidation implements TravelRequestValidation{

    @Autowired
    ValidationErrorFactory validationErrorFactory;

    @Autowired
    DateServiceUtil dateService;
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        var from = request.getAgreementDateFrom();

        return (from != null) ?
                from.after(dateService.getTodayDate()) ?
                        Optional.empty() :
                        Optional.of(validationErrorFactory.buildError(PAST_DATE, new Placeholder("fieldName", "agreementDateFrom"))) :
                Optional.empty();
    }
}
