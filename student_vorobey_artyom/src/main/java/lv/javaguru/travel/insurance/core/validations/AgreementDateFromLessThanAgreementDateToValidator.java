package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class AgreementDateFromLessThanAgreementDateToValidator implements TravelRequestValidation{

    @Autowired
    private ValidationErrorFactory factory;

    @Override
    public Optional<ValidationError> validateArgs(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        return (dateTo != null && dateFrom != null
                && (dateFrom.equals(dateTo) || dateFrom.after(dateTo)))
                ? Optional.of(factory.buildError("ERROR_CODE_5"))
                : Optional.empty();
    }
}
