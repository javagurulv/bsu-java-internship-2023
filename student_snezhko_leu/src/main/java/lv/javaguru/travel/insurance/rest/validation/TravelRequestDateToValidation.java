package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TravelRequestDateToValidation extends TravelRequestValidationImpl {
    @Autowired
    ValidationErrorFactory errorFactory;
    /*
    @Autowired

    ValidationErrorsUtil util;
     */
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
//        ValidationErrorsUtil util = new ValidationErrorsUtil();
 /*       ValidationErrorsUtil util;
        try {
            util = new ValidationErrorsUtil();
        }
        catch (IOException e) {
            return Optional.of(new ValidationError("ERROR_CODE_0", "IOException is errorCode.properties!"));//util.buildError("ERROR_CODE_3"));
        }
*/

//        errorFactory = new ValidationErrorFactory();
        if (request.getAgreementDateTo() == null) {
            String errorCode = "ERROR_CODE_5";
            return Optional.of(errorFactory.buildError(errorCode));
        }
        else if (request.getAgreementDateTo().before(request.getAgreementDateFrom())) {
            String errorCode = "ERROR_CODE_6";
            return Optional.of(errorFactory.buildError(errorCode));
        }
        return Optional.empty();
    }
}
