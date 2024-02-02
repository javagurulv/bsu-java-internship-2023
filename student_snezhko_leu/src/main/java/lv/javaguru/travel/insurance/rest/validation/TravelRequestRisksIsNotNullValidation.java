package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TravelRequestRisksIsNotNullValidation extends TravelRequestValidationImpl {
    /*
    @Autowired

    ValidationErrorsUtil util;
     */
    @Autowired
    ValidationErrorFactory errorFactory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        //Optional<ValidationError> result = Optional.empty();
        //ValidationErrorsUtil util = new ValidationErrorsUtil();
        /*
        ValidationErrorsUtil util;
        try {
            util = new ValidationErrorsUtil();
        }
        catch (IOException e) {
            return Optional.of(new ValidationError("ERROR_CODE_0", "IOException is errorCode.properties!"));//util.buildError("ERROR_CODE_3"));
        }
*/

//        errorFactory = new ValidationErrorFactory();

        if (request.getSelectedRisks() == null) {
            String errorCode = "ERROR_CODE_8";
            return Optional.of(errorFactory.buildError(errorCode));
        }
        else if (request.getSelectedRisks().isEmpty()) {
            String errorCode = "ERROR_CODE_7";
            return Optional.of(errorFactory.buildError(errorCode));
        }
        //return result;
        return Optional.empty();
    }
}
