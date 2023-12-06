package lv.javaguru.travel.insurance.core.validations;


import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.util.ErrorCodeValueUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
//import lv.javaguru.travel.insurance.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public
class ValidationErrorFactory {

    @Autowired private ErrorCodeValueUtil errorCodeUtil;

    public ValidationErrorDto buildError(String errorCode) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationErrorDto(errorCode, errorDescription);
    }

    public ValidationErrorDto buildError(String errorCode, List<Placeholder> placeholders) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode, placeholders);
        return new ValidationErrorDto(errorCode, errorDescription);
    }

}

