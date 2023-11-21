package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationErrorFactory {
    @Autowired
    private ErrorCodesPropertiesReader reader;
    public ValidationError constructError(String errCode){
        return new ValidationError(errCode, reader.getDescription(errCode));
    }
}
