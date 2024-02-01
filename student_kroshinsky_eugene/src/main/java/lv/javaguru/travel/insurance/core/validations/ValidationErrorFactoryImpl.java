package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.utils.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ValidationErrorFactoryImpl implements ValidationErrorFactory {
    @Autowired
    ErrorCodeUtil errorCodeUtil;
    @Override
    public ValidationError createValidationError(String errorCode) {
        return new ValidationError(errorCode, errorCodeUtil.getProperty(errorCode));
    }

    @Override
    public ValidationError buildError(String errorCode, List<Placeholder> placeholders) {
        return new ValidationError(errorCode, errorCodeUtil.getErrorDescription(errorCode, placeholders));
    }
}
