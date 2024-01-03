package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.core.util.ValidationErrorsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationErrorFactory {
    @Autowired
    private ValidationErrorsUtil util;

    public ValidationError buildError(String errorCode) {
        return new ValidationError(errorCode, util.getDescriptionByErrorCode(errorCode));
    }
}
