package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TravelRequestLastNameValidation extends TravelRequestValidationImpl {
/*    @Autowired
    ValidationErrorsUtil util;
*/
@Autowired
ValidationErrorFactory errorFactory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        //errorFactory = new ValidationErrorFactory();
        if (request.getPersonLastName() == null || request.getPersonLastName().isEmpty()) {
           /*
            ValidationErrorsUtil util;
            try {
                util = new ValidationErrorsUtil();
            }
            catch (IOException e) {
                return Optional.of(new ValidationError("ERROR_CODE_0", "IOException is errorCode.properties!"));//util.buildError("ERROR_CODE_3"));
            }
*/

            //ValidationErrorsUtil util = new ValidationErrorsUtil();
            String errorCode = "ERROR_CODE_2";
            return Optional.of(errorFactory.buildError(errorCode));
        }
        return Optional.empty();
    }
}
