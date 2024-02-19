package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class AgreementDateFromIsNotInPastValidator implements TravelRequestValidation{

    @Autowired
    private ValidationErrorFactory factory;

    @Override
    public Optional<ValidationError> validateArgs(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date currentDate = new Date();
        return (dateFrom != null && dateFrom.getTime() < currentDate.getTime())
                ? Optional.of(factory.buildError("ERROR_CODE_6"))
                : Optional.empty();
    }
}
