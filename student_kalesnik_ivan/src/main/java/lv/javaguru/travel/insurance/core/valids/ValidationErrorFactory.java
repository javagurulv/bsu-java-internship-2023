package lv.javaguru.travel.insurance.core.valids;


import lv.javaguru.travel.insurance.core.util.ErrorCodeValueUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public
class ValidationErrorFactory {

    @Autowired private ErrorCodeValueUtil errorCodeUtil;

    public ValidationError buildError(String errorCode) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationError(errorCode, errorDescription);
    }

    public ValidationError buildError(String errorCode, List<Placeholder> placeholders) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode, placeholders);
        return new ValidationError(errorCode, errorDescription);
    }

}

