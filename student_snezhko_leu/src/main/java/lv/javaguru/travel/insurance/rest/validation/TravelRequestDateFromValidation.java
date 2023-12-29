package lv.javaguru.travel.insurance.rest.validation;

import lombok.Setter;
import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelRequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.io.IOException;


@Setter
@Component
public class TravelRequestDateFromValidation implements TravelRequestValidation {
    @Autowired
    ValidationErrorsUtil util;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) throws IOException{
        util = new ValidationErrorsUtil();
        if (request.getAgreementDateFrom() == null) {
            String errorCode = "ERROR_CODE_3";
            return Optional.of(new ValidationError(errorCode, util.getDescriptionByErrorCode(errorCode)));//util.buildError("ERROR_CODE_3"));
        }
        else if (request.getAgreementDateFrom().compareTo(new Date()) <= 0) {
            String errorCode = "ERROR_CODE_4";
            return Optional.of(new ValidationError(errorCode, util.getDescriptionByErrorCode(errorCode)));
        }
        return Optional.empty();
    }
}
