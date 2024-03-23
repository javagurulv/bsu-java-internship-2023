package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ValidationErrorFactory {
    @Autowired
    private ErrorCodeUtil errorCodeUtil;

    ValidationError createError(String errorCode) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationError(errorCode, errorDescription);
    }
    ValidationError createError(String errorCode, List<Placeholder> placeholderList) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode, placeholderList);
        return  new ValidationError(errorCode, errorDescription);
    }
}
