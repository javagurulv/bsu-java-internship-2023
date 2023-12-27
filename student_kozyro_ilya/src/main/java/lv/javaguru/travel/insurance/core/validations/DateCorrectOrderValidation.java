package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.services.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static lv.javaguru.travel.insurance.core.validations.errors.ValidationErrorCodes.*;

@Component
public class DateCorrectOrderValidation implements TravelRequestValidation{
    @Autowired
    ValidationErrorFactory validationErrorFactory;
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        var from = travelCalculatePremiumRequest.getAgreementDateFrom();
        var to = travelCalculatePremiumRequest.getAgreementDateTo();

        return ((from != null && to != null) && (from.after(to) || from.equals(to))) ?
                Optional.of(validationErrorFactory.buildError(NOT_CORRECT_DATE_ORDER)) :
                Optional.empty();

    }
}
