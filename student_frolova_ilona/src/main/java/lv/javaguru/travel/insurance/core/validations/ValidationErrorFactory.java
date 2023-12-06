package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorManager;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationErrorFactory {

    @Autowired
    private ErrorManager errorManager;

    public ValidationError buildError(String errorCode) {
        return new ValidationError(
                errorCode,
                errorManager.getErrorDescription(errorCode)
        );
    }
}
