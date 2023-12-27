package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.utils.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ValidationErrorFactoryImpl implements ValidationErrorFactory {
    @Autowired
    ErrorCodeUtil propertyReader;
    @Override
    public ValidationError createValidationError(String errorCode) {
        return new ValidationError(errorCode, propertyReader.getProperty(errorCode));
    }
}
