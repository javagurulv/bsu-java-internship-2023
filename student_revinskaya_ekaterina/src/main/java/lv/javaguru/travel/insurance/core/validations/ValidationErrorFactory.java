package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationErrorFactory {
    @Autowired
    private ErrorCodeUtil reader;
    public ValidationError buildError(String errCode){
        return new ValidationError(errCode, reader.getErrorDescription(errCode));
    }
    public ValidationError buildError(String errorCode, List<Placeholder> placeholders){
        return new ValidationError(errorCode, reader.getErrorDescription(errorCode, placeholders));
    }

}
