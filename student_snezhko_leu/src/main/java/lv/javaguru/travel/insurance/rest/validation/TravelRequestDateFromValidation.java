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


@Component
public class TravelRequestDateFromValidation extends TravelRequestValidationImpl {
    @Autowired
    ValidationErrorFactory errorFactory;
    /*
    @Autowired
    ValidationErrorsUtil util;
    */
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        //errorFactory = new ValidationErrorFactory();
        /*ValidationErrorsUtil util;
        try {
            util = new ValidationErrorsUtil();
        }
        catch (IOException e) {
            return Optional.of(new ValidationError("ERROR_CODE_0", "IOException in errorCode.properties!"));//util.buildError("ERROR_CODE_3"));
        }
*/
        if (request.getAgreementDateFrom() == null) {
                String errorCode = "ERROR_CODE_3";
                return Optional.of(errorFactory.buildError(errorCode));
            }
            else if (request.getAgreementDateFrom().compareTo(new Date()) <= 0) {
                String errorCode = "ERROR_CODE_4";
                return Optional.of(errorFactory.buildError(errorCode));
            }

        return Optional.empty();
    }
}
