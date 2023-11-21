package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationErrorFactory {
    @Autowired
    private ErrorCodeUtil reader;
    public ValidationError constructError(String errCode){
        return new ValidationError(errCode, reader.getDescription(errCode));
    }
}
